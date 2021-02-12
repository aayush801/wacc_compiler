package errors.semantic_errors;

import org.antlr.v4.runtime.Token;

public class InvalidArguments extends WaccSemanticError {

  protected String func;
  protected int required, given;

  public InvalidArguments(Token token, String func, int required, int given) {
    super(token);
    this.func = func;
    this.required = required;
    this.given = given;
  }

  @Override
  public String getErrorMessage() {
    return "invalid number of parameters for '" + func + "' given."
        + " WAS : " + given + ", EXPECTED : " + required;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof InvalidArguments) {
      InvalidArguments e = (InvalidArguments) o;
      return func.equals(e.func) && given == e.given && required == e.required;
    }
    return false;
  }
}
