package middleware.pair_ast;

import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.Move;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.instructions.addr_modes.ZeroOffset;
import backend.labels.code.PrimitiveLabel;
import backend.operands.ImmediateNum;
import backend.primitive_functions.PairElemNullAccessCheck;
import backend.primitive_functions.PrintFunctions;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.PAIR;
import java.util.ArrayList;
import java.util.List;
import middleware.ExpressionAST;
import middleware.NodeAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class PairElemAST extends NodeAST {

  private final ExpressionAST expr;
  TYPE type;
  int index;

  public PairElemAST(ParserRuleContext ctx, ExpressionAST expr, int index) {
    super(ctx);
    this.expr = expr;
    this.index = index;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    // check the expression.
    expr.check();

    // ensure that the expressions is a PAIR.
    IDENTIFIER exprType = expr.getType();

    if (!(exprType instanceof PAIR)) {

      addError(new MismatchedTypes(ctx, exprType, new PAIR(new TYPE(), new TYPE())));

      return;

    }

    // If e got exprType must be a PAIR.
    PAIR pair = (PAIR) exprType;

    // call getFirst or getSecond based on the index.
    if (index == 0) {
      type = pair.getFirst();
    } else {
      type = pair.getSecond();
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {

    Register target = registers.get(0);

    // evaluate the expression.
    List<Instruction> ret = expr.translate(registers);

    // Move result into r0
    ret.add(new Move(Register.R0, target));

    // Branch to null check
    PrimitiveLabel checkNullPrimitive = PairElemNullAccessCheck.pairElemCheckProgram(program);
    ret.add(new Branch(checkNullPrimitive.getLabelName(), true));
    program.addPrimitive(checkNullPrimitive);

    // Load appropriate address into target.
    ret.add(new Load(target, new ImmediateOffset(target,
        new ImmediateNum(index * 4))));


    return ret;
  }

}
