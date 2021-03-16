package wacc.errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class VisibilityError extends WaccSemanticError{

  public VisibilityError(ParserRuleContext ctx) {
    super(ctx);
  }

  @Override
  public String getErrorMessage() {
    return "is not accessible from this scope";
  }
}
