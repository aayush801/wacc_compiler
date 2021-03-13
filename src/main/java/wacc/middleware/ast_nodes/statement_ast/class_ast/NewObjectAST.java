package wacc.middleware.ast_nodes.statement_ast.class_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import org.antlr.v4.runtime.ParserRuleContext;

public class NewObjectAST extends ExpressionAST {

  private final String className;
  private final NodeASTList<ExpressionAST> actuals;

  public NewObjectAST(List<WaccError> errors,ParserRuleContext ctx,
      String className, NodeASTList<ExpressionAST> actuals) {
    super(errors, ctx);
    this.className = className;
    this.actuals = actuals;
  }

  @Override
  public void check() {

  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
