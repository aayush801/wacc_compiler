package wacc.errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class InvalidImport extends WaccSemanticError{
  private final String filepath;

  public InvalidImport(ParserRuleContext ctx, String filepath) {
    super(ctx);
    this.filepath = filepath;
  }

  @Override
  public String getErrorMessage() {
    return filepath + " is not a valid import";
  }
}
