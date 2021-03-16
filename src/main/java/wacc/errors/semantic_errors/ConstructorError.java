package wacc.errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class ConstructorError extends WaccSemanticError{

  public ConstructorError(ParserRuleContext ctx) {
    super(ctx);
  }

  public ConstructorError(ParserRuleContext ctx, String offendingSymbol) {
    super(ctx, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return "constructor has been incorrectly defined";
  }
}
