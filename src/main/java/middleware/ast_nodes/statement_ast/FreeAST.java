package middleware.ast_nodes.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.PAIR;
import middleware.ExpressionAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class FreeAST extends StatementAST {

  private final ExpressionAST expr;

  public FreeAST(ParserRuleContext ctx, ExpressionAST expr) {
    super(ctx);
    this.expr = expr;
  }

  @Override
  public void check() {

    // Verify that the given expression is valid.
    expr.check();

    // Get the type of the expression.
    IDENTIFIER type = expr.getType();

    if (type != null) {

      if (!(type instanceof PAIR)) {
        // expression is not a pair.
        addError(new MismatchedTypes(ctx, type, new PAIR()));
      }

    }

  }

  public ExpressionAST getExpr() {
    return expr;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
