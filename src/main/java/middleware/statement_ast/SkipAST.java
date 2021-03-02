package middleware.statement_ast;

import backend.NodeASTVisitor;
import middleware.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

/*
 * Basic node for the skip statement.
 */
public class SkipAST extends StatementAST {


  public SkipAST(ParserRuleContext ctx) {
    super(ctx);
  }

  @Override
  public void check() {
  }
  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
