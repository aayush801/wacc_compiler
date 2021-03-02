package middleware.arrays_ast;

import backend.NodeASTVisitor;
import backend.instructions.Branch;
import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.Move;
import backend.instructions.Store;
import backend.instructions.addr_modes.ImmediateAddress;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.instructions.addr_modes.ZeroOffset;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.ARRAY;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import java.util.ArrayList;
import java.util.List;
import middleware.ExpressionAST;
import middleware.NodeAST;
import middleware.NodeASTList;
import org.antlr.v4.runtime.ParserRuleContext;

public class ArrayAST extends NodeAST {



  private final NodeASTList<ExpressionAST> expressionASTList;
  private ARRAY arrayObj;

  public ArrayAST(ParserRuleContext ctx,
      NodeASTList<ExpressionAST> expressionASTList) {
    super(ctx);
    this.expressionASTList = expressionASTList;
  }

  public NodeASTList<ExpressionAST> getExpressionASTList() {
    return expressionASTList;
  }

  public ARRAY getArrayObj() {
    return arrayObj;
  }

  @Override
  public void check() {

    // Case where an empty array is created.
    if (expressionASTList.isEmpty()) {

      arrayObj = new ARRAY(new TYPE());

    } else {
      // Array has >0 elements.

      // check the first element.
      expressionASTList.get(0).check();

      // Verify that the first element's type is a TYPE.
      TYPE curType = expressionASTList.get(0).getType();

      if (curType == null) {
        addError(new MismatchedTypes(expressionASTList.get(0).ctx, curType,
            new TYPE()));

      } else {

        // Check that all other elements of the array have the same type as
        // the first element.
        int arraySize = expressionASTList.size();
        for (int i = 1; i < arraySize; i++) {
          ExpressionAST expressionAST = expressionASTList.get(i);
          expressionAST.check();
          if (!(isCompatible(expressionAST.getType(), curType))) {
            addError(
                new MismatchedTypes(
                    expressionAST.ctx,
                    expressionAST.getType(),
                    curType)
            );
          }
        }

        // create a new ARRAY object and store it.
        arrayObj = new ARRAY(curType);
      }
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    Register destination = registers.get(0);
    List<Instruction> ret = new ArrayList<>();

    // Calculate size of heap space required.
    int length = expressionASTList.size();
    int elementSize = arrayObj.getType().getSize();
    int size = length * elementSize + 4;

    ret.add(new Load(Register.R0, new ImmediateAddress(size)));

    // Add malloc instruction.
    ret.add(new Branch("malloc", true));

    // Store result from malloc in destination.
    ret.add(new Move(destination, Register.R0));

    List<Register> remainingRegs = new ArrayList<>(registers);
    remainingRegs.remove(0);
    Register target = remainingRegs.get(0);

    // Store parameters on the heap
    ExpressionAST e;
    for (int i = 0; i < length; i++) {
      e = expressionASTList.get(i);
      ret.addAll(e.translate(remainingRegs));
      ret.add(new Store(ConditionCode.NONE, target,
          new ImmediateOffset(destination,
              new ImmediateNum(4 + i * elementSize)),
          arrayObj.getType().getSize()));
    }

    // Store size of array on the starting address of the heap entry.
    ret.add(new Load(target, new ImmediateAddress(length)));

    // storing address of the array.
    ret.add(new Store(target, new ZeroOffset(destination)));

    return ret;
  }

  @Override
  public List<Instruction> accept(NodeASTVisitor visitor) {
    return (List<Instruction>) visitor.visit(this);
  }
}
