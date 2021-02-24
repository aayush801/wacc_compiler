package middleware;

import org.antlr.v4.runtime.ParserRuleContext;

/* Superclass of all statement AST classes.
 */
public abstract class StatementAST extends NodeAST {

  public StatementAST(ParserRuleContext ctx) {
    super(ctx);
  }
}
