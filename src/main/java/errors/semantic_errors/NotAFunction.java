package errors.semantic_errors;

import org.antlr.v4.runtime.Token;

public class NotAFunction extends WaccSemanticError {


  public NotAFunction(Token token) {
    super(token);
  }

  public NotAFunction(Token token, String offendingSymbol) {
    super(token, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return " is not a function";
  }

}
