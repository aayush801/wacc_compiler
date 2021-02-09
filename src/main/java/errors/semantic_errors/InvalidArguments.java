package errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class InvalidArguments extends WaccSemanticError {

  protected String func;
  protected int required, given;

  public InvalidArguments(ParserRuleContext ctx, String func, int required, int given) {
    super(ctx);
    this.func = func;
    this.required = required;
    this.given = given;
  }

  @Override
  public String toString() {
    return super.toString() + "invalid number of parameters for '" + func + "' given."
        + " WAS : " + given + ", EXPECTED : " + required;
  }
}
