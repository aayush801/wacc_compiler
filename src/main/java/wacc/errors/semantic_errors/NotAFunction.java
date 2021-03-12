package wacc.errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class NotAFunction extends WaccSemanticError {

  public NotAFunction(ParserRuleContext ctx) {
    super(ctx);
  }

  @Override
  public String getErrorMessage() {
    return " is not a function";
  }

}
