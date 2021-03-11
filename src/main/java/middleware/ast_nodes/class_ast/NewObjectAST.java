package middleware.ast_nodes.class_ast;

import frontend.identifier_objects.CLASS;
import middleware.ExpressionAST;
import middleware.NodeAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.NodeASTList;
import org.antlr.v4.runtime.ParserRuleContext;

public class NewObjectAST extends ExpressionAST {

  private final String className;
  private final NodeASTList<ExpressionAST> actuals;

  public NewObjectAST(ParserRuleContext ctx, String className,
      NodeASTList<ExpressionAST> actuals) {
    super(ctx);
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
