package errors.semantic_errors;

import org.antlr.v4.runtime.Token;

public class GlobalScope extends WaccSemanticError {

  public GlobalScope(Token token) {
    super(token);
  }

  public GlobalScope(Token token, String offendingSymbol) {
    super(token, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return "Cannot return from the global scope.";
  }
}
