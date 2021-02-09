package errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class Undefined extends WaccSemanticError {

  public Undefined(ParserRuleContext ctx) {
    super(ctx);
  }

  public Undefined(ParserRuleContext ctx, String partOfCodeWithError) {
    super(ctx, partOfCodeWithError);
  }

  public Undefined(String code) {
    this.code = code;
  }

  @Override
  public String getErrorMessage() {
    return "\"" + code + "\" is undefined";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Undefined) {
      Undefined e = (Undefined) o;
      return code.equals(e.code);
    }
    return false;
  }
}
