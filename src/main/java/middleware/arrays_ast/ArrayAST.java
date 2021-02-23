package middleware.arrays_ast;

import backend.instructions.*;
import backend.instructions.addr_modes.ImmediateAddress;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.instructions.addr_modes.RegisterOffset;
import backend.instructions.addr_modes.ZeroOffset;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.ARRAY;
import java.util.ArrayList;
import java.util.List;

import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import middleware.NodeAST;
import middleware.NodeASTList;
import middleware.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class ArrayAST extends NodeAST {

  private final NodeASTList<ExpressionAST> expressionASTList;
  private ARRAY arrayObj;

  public ArrayAST(ParserRuleContext ctx, NodeASTList<ExpressionAST> expressionASTList) {
    super(ctx);
    this.expressionASTList = expressionASTList;
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
      IDENTIFIER curType = expressionASTList.get(0).getType();

      if (!(curType instanceof TYPE)) {
        addError(new MismatchedTypes(expressionASTList.get(0).ctx, curType, new TYPE()));

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
        arrayObj = new ARRAY((TYPE) curType);

      }
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    Register destination = registers.get(0);
    List<Instruction> ret = new ArrayList<>();

    // Calculate size of heap space required.
    int elements = expressionASTList.size();
    int size = elements*arrayObj.getType().getSize() + arrayObj.getSize();
    Instruction malloc_size = new Load(new Register(0), new ImmediateAddress(size));
    ret.add(malloc_size);

    // Add malloc instruction.
    ret.add(new Branch("malloc", true));

    // Store result from malloc in destination.
    ret.add(new Move(destination, new Register(0)));

    int soFar = 4;
    List<Register> remainingRegs = new ArrayList<>(registers);
    remainingRegs.remove(0);
    Register target = remainingRegs.get(0);

    boolean charOrBool = arrayObj.getType() instanceof CHAR
            || arrayObj.getType() instanceof BOOL;

    // Store parameters on the heap
    for (ExpressionAST e : expressionASTList) {
      ret.addAll(e.translate(remainingRegs));
      ret.add(new Store(ConditionCode.NONE, target, new ImmediateOffset(destination, new ImmediateNum(soFar)), charOrBool));
      soFar += arrayObj.getType().getSize();
    }

    // Store size of array on the starting address of the heap entry.
    ret.add(new Load(target, new ImmediateAddress(elements)));
    ret.add(new Store(target, new ZeroOffset(destination)));

    return ret;
  }
}
