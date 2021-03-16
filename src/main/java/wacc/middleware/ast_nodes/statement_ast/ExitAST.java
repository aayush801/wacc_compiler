package wacc.middleware.ast_nodes.statement_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.basic_types.INT;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ExitAST extends StatementAST {

  private final ExpressionAST expressionAST;

  public ExitAST(List<WaccError> errors, ParserRuleContext ctx, ExpressionAST expressionAST) {
    super(errors, ctx);
    this.expressionAST = expressionAST;
  }

  public ExpressionAST getExpressionAST() {
    return expressionAST;
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
