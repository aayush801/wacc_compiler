package errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class InvalidArguments extends WaccSemanticError {

  protected String func;
  protected int required, given;

  public InvalidArguments(ParserRuleContext ctx, String func, int required, int given) {
    super(ctx);
    setValues(func, required, given);
  }

  private void setValues(String func, int required, int given) {
    this.func = func;
    this.required = required;
    this.given = given;
  }

  public InvalidArguments(String func, int required, int given) {
    setValues(func, required, given);
  }

  @Override
  public String toString() {
    return super.toString() + "invalid number of parameters for '" + func + "' given."
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
