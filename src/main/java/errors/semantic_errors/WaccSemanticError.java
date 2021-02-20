package errors.semantic_errors;

import errors.WaccError;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class WaccSemanticError extends WaccError {

  public WaccSemanticError(ParserRuleContext ctx) {
    super(ctx);
  }

  public WaccSemanticError(ParserRuleContext ctx, String offendingSymbol) {
    super(ctx, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return toString();
  }

  @Override
  public String toString() {
    return "Semantic Error" + super.toString();
  }
}
