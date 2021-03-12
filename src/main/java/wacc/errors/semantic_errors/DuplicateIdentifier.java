package wacc.errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class DuplicateIdentifier extends WaccSemanticError {

  public DuplicateIdentifier(ParserRuleContext ctx) {
    super(ctx);
  }

  @Override
  public String getErrorMessage() {
    return " identifier is already defined";
  }
}
