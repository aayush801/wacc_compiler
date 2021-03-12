package wacc.errors.semantic_errors;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Utils;
import wacc.errors.WaccError;

public class ImportBroken extends WaccSemanticError {

  List<WaccError> importErrors;

  public ImportBroken(ParserRuleContext ctx, List<WaccError> importErrors) {
    super(ctx);
    this.importErrors = importErrors;
  }

  public ImportBroken(ParserRuleContext ctx, String offendingSymbol, List<WaccError> importErrors) {
    super(ctx, offendingSymbol);
    this.importErrors = importErrors;
  }

  @Override
  public String getErrorMessage() {
    StringBuilder builder = new StringBuilder();
    builder.append("Imported file is broken, it has the following errors\n");

    importErrors.forEach(e -> builder.append(e).append("\n"));

    return builder.toString();
  }
}
