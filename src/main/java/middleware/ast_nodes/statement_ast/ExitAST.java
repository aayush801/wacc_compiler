package middleware.ast_nodes.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.INT;
import middleware.ExpressionAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ExitAST extends StatementAST {

  private final ExpressionAST expressionAST;

  public ExitAST(ParserRuleContext ctx, ExpressionAST expressionAST) {
    super(ctx);
    this.expressionAST = expressionAST;
  }

  @Override
  public void check() {

    // Verify that the expr passed to exit is a valid expression.
    expressionAST.check();
    IDENTIFIER type = expressionAST.getType();
    if (type == null) {
      return;
    }

    // Verify that the expression is an INT.
    if (!(type instanceof INT)) {
      addError(new MismatchedTypes(
          expressionAST.ctx, expressionAST.getType(), new INT())
      );
    }

  }

  public ExpressionAST getExpr() {
    return expressionAST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }


}
