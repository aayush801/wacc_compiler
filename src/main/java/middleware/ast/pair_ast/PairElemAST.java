package middleware.ast.pair_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.IDENTIFIER;
import identifier_objects.basic_types.PAIR;
import identifier_objects.TYPE;
import middleware.ast.NodeAST;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class PairElemAST extends NodeAST {

  TYPE type;
  private final ExpressionAST expr;
  int index;

  public PairElemAST(Token token, ExpressionAST expr, int index) {
    super(token);
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

      addError(new MismatchedTypes(token, exprType, new PAIR(new TYPE(), new TYPE())));

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
}
