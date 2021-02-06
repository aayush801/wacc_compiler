package error;

import org.antlr.v4.runtime.ParserRuleContext;

public class DuplicateIdentifier extends WaccError{

  public DuplicateIdentifier(ParserRuleContext ctx) {
    super(ctx);
  }

  public DuplicateIdentifier(ParserRuleContext ctx, String partOfCodeWithError) {
    super(ctx, partOfCodeWithError);
  }

  @Override
  public String getErrorMessage() {
    return "\"" + code + "\" identifier is already defined";
  }
}
