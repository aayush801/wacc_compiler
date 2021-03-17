package wacc.errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class VisibilityError extends WaccSemanticError{

  public VisibilityError(ParserRuleContext ctx) {
    super(ctx);
  }

  public VisibilityError(ParserRuleContext ctx, String offendingSymbol) {
    super(ctx, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return "is not accessible from this scope";
  }
}
