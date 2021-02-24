package errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class NotAFunction extends WaccSemanticError {


  public NotAFunction(ParserRuleContext ctx) {
    super(ctx);
  }

  public NotAFunction(ParserRuleContext ctx, String offendingSymbol) {
    super(ctx, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return " is not a function";
  }

}
