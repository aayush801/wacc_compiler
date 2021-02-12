package errors.semantic_errors;

import errors.WaccError;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class WaccSemanticError extends WaccError {

  public WaccSemanticError(Token token) {
    super(token);
  }

  public WaccSemanticError(Token token, String offendingSymbol) {
    super(token, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return toString();
  }

  @Override
  public String toString() {
    return "Semantic Error" + super.toString();
  }
}
