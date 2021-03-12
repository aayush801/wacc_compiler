package wacc.middleware.ast_nodes.statement_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.basic_types.PAIR;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class FreeAST extends StatementAST {

  private final ExpressionAST expr;

  public FreeAST(List<WaccError> errors, ParserRuleContext ctx, ExpressionAST expr) {
    super(errors, ctx);
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
