package errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class Undefined extends WaccSemanticError {

  public Undefined(ParserRuleContext ctx) {
    super(ctx);
  }

  public Undefined(ParserRuleContext ctx, String partOfCodeWithError) {
    super(ctx, partOfCodeWithError);
  }

  @Override
  public String getErrorMessage() {
    return " is undefined";
  }

}
