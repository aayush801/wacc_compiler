package errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class MissingReturnStatement extends WaccSemanticError {


  public MissingReturnStatement(ParserRuleContext ctx) {
    super(ctx);
  }

  public MissingReturnStatement(ParserRuleContext ctx, String offendingSymbol) {
    super(ctx, offendingSymbol);
  }

  @Override
  public String getErrorMessage() {
    return " missing return statement.";
  }
}
