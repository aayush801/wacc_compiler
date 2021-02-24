package errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class GlobalScope extends WaccSemanticError {

  public GlobalScope(ParserRuleContext ctx) {
    super(ctx);
  }

  public GlobalScope(ParserRuleContext ctx, String offendingSymbol) {
    super(ctx, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return "Cannot return from the global scope.";
  }
}
