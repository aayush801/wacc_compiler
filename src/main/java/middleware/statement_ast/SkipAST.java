package middleware.statement_ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class SkipAST extends StatementAST {

  // basic node for the skip statement.

  public SkipAST(ParserRuleContext ctx) {
    super(ctx);
  }

  @Override
  public void check() {
  }

}
