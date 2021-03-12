package wacc.errors.syntax_errors;

import wacc.errors.WaccError;
import wacc.errors.semantic_errors.WaccSemanticError;

public class WaccSyntaxError extends WaccError {

  private final String msg;

  public WaccSyntaxError(Object offendingSymbol, int line,
      int charPositionInLine, String msg) {
    super(line, charPositionInLine, offendingSymbol.toString());
    this.msg = msg;
  }

  @Override
  public String getErrorMessage() {
    return msg;
  }

  @Override
  public String toString() {
    return "Syntax Error " + super.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof WaccSemanticError) {
      return getErrorMessage().equals(((WaccSemanticError) o)
          .getErrorMessage());
    }
    return false;
  }
}
