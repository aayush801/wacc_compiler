package middleware.statement_ast;

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

}
