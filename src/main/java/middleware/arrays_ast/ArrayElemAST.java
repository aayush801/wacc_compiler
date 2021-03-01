package middleware.arrays_ast;

import backend.instructions.Branch;
import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.Move;
import backend.instructions.addr_modes.ZeroOffset;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.labels.code.PrimitiveLabel;
import backend.operands.ImmediateNum;
import backend.operands.ImmediateNumLSL;
import backend.primitive_functions.PrintArrayBoundsChecks;
import backend.registers.Register;
import backend.registers.StackPointer;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.STACK_OBJECT;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import frontend.identifier_objects.basic_types.ARRAY;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import java.util.ArrayList;
import java.util.List;
import middleware.ExpressionAST;
import middleware.NodeAST;
import middleware.NodeASTList;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class ArrayElemAST extends ExpressionAST {

  private final String arrayName;
  private final NodeASTList<ExpressionAST> expressionASTS;
  public TYPE type;
  private SymbolTable scopeST;
  private boolean requiresDereference = true;

  public ArrayElemAST(ParserRuleContext ctx, String arrayName,
      NodeASTList<ExpressionAST> expressionASTS) {
    super(ctx);
    this.arrayName = arrayName;
    this.expressionASTS = expressionASTS;
  }


  public void setDereference(boolean requiresDereference) {
    this.requiresDereference = requiresDereference;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    scopeST = ST;

    // lookup the array name in the symbol table.
    IDENTIFIER array = NodeAST.ST.lookupAll(arrayName);

    // Verify that array name is found.
    if (array == null) {
      addError(new Undefined(ctx, arrayName));
      return;
    }

    // If array is stored as a VARIABLE in the ST, get the real type.
    if (array instanceof VARIABLE) {
      array = ((VARIABLE) array).getType();
    }

    // If array is stored as a PARAM in the ST, get the real type.
    if (array instanceof PARAM) {
      array = ((PARAM) array).getType();
    }

    // Verify that array is an ARRAY at this point.
    if (!(array instanceof ARRAY)) {
      addError(new MismatchedTypes(ctx, array, new ARRAY(new TYPE())));
      return;
    }

    // Verify that any given expressions are INTs.
    for (ExpressionAST expressionAST : expressionASTS) {
      expressionAST.check();
      if (!(expressionAST.getType() instanceof INT)) {
        addError(
            new MismatchedTypes(
                expressionAST.ctx,
                expressionAST.getType(),
                new INT())
        );
      }

      // get the base element type of the array (even if nested)
      array = ((ARRAY) array).getType();

    }

    type = (TYPE) array;

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    List<Instruction> ret = new ArrayList<>();

    // target is where we store the pointer to the heap entry.
    Register target = registers.get(0);

    // setting target to point to the array we want to index.
    STACK_OBJECT varStackObj = (STACK_OBJECT) scopeST.lookupAll(arrayName);

    if (!varStackObj.isLive()) {
      // if the object is not live yet, then we must be referencing an even older declaration
      varStackObj = (STACK_OBJECT) scopeST.getEncSymTable().lookupAll(arrayName);
    }

    int offset = program.SP.calculateOffset(varStackObj.getStackAddress());
    ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, new StackPointer(),
        new ImmediateNum(offset), false));

    List<Register> remainingRegs = new ArrayList<>(registers);
    remainingRegs.remove(0);
    // index is where we store the index of the array.
    Register index = registers.get(1);

    // calculate index, and set it to the register index.
    // TODO: ADD CODE FOR DOING 2D ARRAYS!!!!!
    for (int i = 0; i < expressionASTS.size(); i++) {
      ExpressionAST exprAST = expressionASTS.get(i);
      ret.addAll(exprAST.translate(remainingRegs));

      ret.add(new Load(target, new ZeroOffset(target)));

      // Array index checking
      ret.add(new Move(Register.R0, index));
      ret.add(new Move(Register.R1, target));

      // include primitive array bounds checker
      PrimitiveLabel arrayBoundsPrimitive = PrintArrayBoundsChecks.printArrayIndexCheck(program);
      ret.add(new Branch(arrayBoundsPrimitive.getLabelName(), true));
      program.addPrimitive(arrayBoundsPrimitive);

      ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, target,
          new ImmediateNum(4), false));

      if (type instanceof CHAR || type instanceof BOOL) {
        ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, target, index,
            false));
      } else {
        ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, target,
            new ImmediateNumLSL(index, 2), false));
      }

    }

    // if arrayElemAST requires dereference
    if (requiresDereference) {
      ret.add(new Load(ConditionCode.NONE, target, new ZeroOffset(target),
          type.getSize()));
    }

    return ret;
  }
}
