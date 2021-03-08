package middleware.ast_nodes.statement_ast;

import middleware.NodeAST;
import middleware.NodeASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class BreakAST extends NodeAST {

  public BreakAST(ParserRuleContext ctx) {
    super(ctx);
  }

  @Override
  public void check() {

  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return null;
  }
}
