package wacc.errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class NotAMethod extends WaccSemanticError {

  public NotAMethod(ParserRuleContext ctx) {
    super(ctx);
  }


  public NotAMethod(ParserRuleContext ctx, String offendingSymbol) {
    super(ctx, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return " is not a method";
  }

}
