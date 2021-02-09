package errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class NotAFunction extends WaccSemanticError {


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

  @Override
  public boolean equals(Object o) {
    if (o instanceof NotAFunction) {
      NotAFunction e = (NotAFunction) o;
      return code.equals(e.code);
    }
    return false;
  }
}