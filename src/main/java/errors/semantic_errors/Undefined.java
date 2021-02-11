package errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Undefined extends WaccSemanticError {

  public Undefined(Token token) {
    super(token);
  }

  public Undefined(Token token, String partOfCodeWithError) {
    super(token, partOfCodeWithError);
  }

  @Override
  public String getErrorMessage() {
    return " is undefined";
  }

}
