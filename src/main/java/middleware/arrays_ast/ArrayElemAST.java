package middleware.arrays_ast;

import backend.primitive_functions.PrintArrayBoundsChecks;
import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.Move;
import backend.instructions.addr_modes.ZeroOffset;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.operands.ImmediateNum;
import backend.operands.ImmediateNumLSL;
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

  public ArrayElemAST(ParserRuleContext ctx, String arrayName,
      NodeASTList<ExpressionAST> expressionASTS) {
    super(ctx);
    this.arrayName = arrayName;
    this.expressionASTS = expressionASTS;
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
    }

    // Finally, update the type.
    type = ((ARRAY) array).getType();

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    List<Instruction> ret = new ArrayList<>();

    // target is where we store the pointer to the heap entry.
    Register target = registers.get(0);

    // setting target to point to the array we want to index.
    STACK_OBJECT varObj = (STACK_OBJECT) scopeST.lookupAll(arrayName);
    int offset = program.SP.calculateOffset(varObj.getStackAddress());
    ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, new StackPointer(),
            new ImmediateNum(offset), false));

    List<Register> remainingRegs = new ArrayList<>(registers);
    remainingRegs.remove(0);
    // index is where we store the index of the array.
    Register index = registers.get(1);

    // calculate index, and set it to the register index.
    // TODO: ADD CODE FOR DOING 2D ARRAYS!!!!!
    ExpressionAST i = expressionASTS.get(0);
    ret.addAll(i.translate(remainingRegs));

    ret.add(new Load(target, new ZeroOffset(target)));

    // Array index checking
    ret.add(new Move(Register.R0, index));
    ret.add(new Move(Register.R1, target));
    ret.add(new Branch("p_check_array_bounds", true));

    ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, target,
        new ImmediateNum(4), false));

    if (type instanceof CHAR || type instanceof BOOL) {
      ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, target, index,
          false));
    } else {
      ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, target,
          new ImmediateNumLSL(index, 2), false));
    }

    PrintArrayBoundsChecks.printArrayNegativeIndexMessage(program);
    PrintArrayBoundsChecks.printArrayTooLargeIndexMessage(program);

    program.addCode(PrintArrayBoundsChecks.printArrayIndexCheck(program));

    return ret;
  }
}
