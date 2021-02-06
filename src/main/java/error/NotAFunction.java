package error;

import org.antlr.v4.runtime.ParserRuleContext;

public class NotAFunction extends WaccError {


  public NotAFunction(ParserRuleContext ctx) {
    super(ctx);
  }

  public NotAFunction(ParserRuleContext ctx, String partOfCodeWithError) {
    super(ctx, partOfCodeWithError);
  }

  @Override
  public String getErrorMessage() {
    return "\"" + code + "\" is not a function";
  }
}
