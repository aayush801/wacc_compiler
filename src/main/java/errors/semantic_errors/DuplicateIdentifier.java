package errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class DuplicateIdentifier extends WaccSemanticError {

  public DuplicateIdentifier(ParserRuleContext ctx) {
    super(ctx);
  }

  public DuplicateIdentifier(ParserRuleContext ctx, String partOfCodeWithError) {
    super(ctx, partOfCodeWithError);
  }

  @Override
  public String getErrorMessage() {
    return " identifier is already defined";
  }
}
