package errors.semantic_errors;

import org.antlr.v4.runtime.Token;

public class DuplicateIdentifier extends WaccSemanticError {

  public DuplicateIdentifier(Token token) {
    super(token);
  }

  public DuplicateIdentifier(Token token, String partOfCodeWithError) {
    super(token, partOfCodeWithError);
  }

  @Override
  public String getErrorMessage() {
    return " identifier is already defined";
  }
}
