package errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class DuplicateIdentifier extends WaccSemanticError {

  public DuplicateIdentifier(ParserRuleContext ctx) {
    super(ctx);
  }

  public DuplicateIdentifier(ParserRuleContext ctx, String partOfCodeWithError) {
    super(ctx, partOfCodeWithError);
  }

  public DuplicateIdentifier(String code) {
    this.code = code;
  }

  @Override
  public String getErrorMessage() {
    return "\"" + code + "\" identifier is already defined";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof DuplicateIdentifier) {
      DuplicateIdentifier e = (DuplicateIdentifier) o;
      return code.equals(e.code);
    }
    return false;
  }
}
