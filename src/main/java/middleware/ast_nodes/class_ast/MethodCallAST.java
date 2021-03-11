package middleware.ast_nodes.class_ast;

import middleware.ExpressionAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.NodeASTList;
import org.antlr.v4.runtime.ParserRuleContext;

public class MethodCallAST extends ExpressionAST {

  private final String object, funcName;
  private final NodeASTList<ExpressionAST> actuals;

  public MethodCallAST(ParserRuleContext ctx, String object, String funcName,
      NodeASTList<ExpressionAST> actuals) {
    super(ctx);
    this.object = object;
    this.funcName = funcName;
    this.actuals = actuals;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
