package wacc.errors.semantic_errors;

import wacc.frontend.identifier_objects.IDENTIFIER;
import org.antlr.v4.runtime.ParserRuleContext;

public class ExpressionNotFound extends WaccSemanticError {

  private final IDENTIFIER actual;

  public ExpressionNotFound(ParserRuleContext ctx, IDENTIFIER actual) {
    super(ctx);
    this.actual = actual;
  }

  @Override
  public String getErrorMessage() {
    return "Expected an expression" +
        ", but got type : " + ((actual == null) ?
        "null" : actual.toString().toUpperCase());
  }

}
