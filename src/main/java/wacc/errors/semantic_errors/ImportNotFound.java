package wacc.errors.semantic_errors;

import org.antlr.v4.runtime.ParserRuleContext;

public class ImportNotFound extends WaccSemanticError {
  private final String filepath;
  public ImportNotFound(ParserRuleContext ctx, String filepath) {
    super(ctx);
    this.filepath = filepath;
  }

  @Override
  public String getErrorMessage() {
    return filepath + " could not be found";
  }
}
