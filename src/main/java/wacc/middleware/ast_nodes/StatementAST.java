package wacc.middleware.ast_nodes;

import java.util.List;
import wacc.errors.WaccError;
import wacc.middleware.NodeAST;
import org.antlr.v4.runtime.ParserRuleContext;

/* Superclass of all statement AST classes.
 */
public abstract class StatementAST extends NodeAST {

  public StatementAST(List<WaccError> errors, ParserRuleContext ctx) {
    super(errors, ctx);
  }
}
