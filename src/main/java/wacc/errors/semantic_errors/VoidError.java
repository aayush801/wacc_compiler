package wacc.errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class VoidError extends WaccSemanticError{

  public VoidError(ParserRuleContext ctx) {
    super(ctx);
  }

  public VoidError(ParserRuleContext ctx, String offendingSymbol) {
    super(ctx, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return "cannot assign a type to void";
  }
}
