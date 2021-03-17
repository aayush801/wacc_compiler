package wacc.errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class OutsideLoop extends WaccSemanticError {

  public OutsideLoop(ParserRuleContext ctx) {
    super(ctx);
  }

  public OutsideLoop(ParserRuleContext ctx, String offendingSymbol) {
    super(ctx, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return " is used outside of a loop";
  }

}
