package errors.semantic_errors;

import frontend.identifier_objects.IDENTIFIER;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class expressionNotFound extends WaccSemanticError {

  private final IDENTIFIER actual;

  public expressionNotFound(ParserRuleContext ctx, IDENTIFIER actual) {
    super(ctx);
    this.actual = actual;
  }

  @Override
  public String getErrorMessage() {
    return "Expected an expression" +
        ", but got type : " + ((actual == null) ? "null" : actual.toString().toUpperCase());
  }

}
