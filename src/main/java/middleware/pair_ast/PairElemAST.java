package middleware.pair_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.PAIR;
import java.util.List;
import middleware.NodeAST;
import middleware.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

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
    return null;
  }

}
