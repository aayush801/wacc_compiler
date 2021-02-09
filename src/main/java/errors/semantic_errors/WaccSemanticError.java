package errors.semantic_errors;

import errors.WaccError;
import org.antlr.v4.runtime.ParserRuleContext;

public class WaccSemanticError extends WaccError {

  public WaccSemanticError(ParserRuleContext ctx) {
    super(ctx);
  }

  public WaccSemanticError(ParserRuleContext ctx, String offendingSymbol) {
    super(ctx, offendingSymbol);
  }

  public WaccSemanticError() {
  }

  @Override
  public String toString() {
    return "Semantic Error " + super.toString();
  }
}
