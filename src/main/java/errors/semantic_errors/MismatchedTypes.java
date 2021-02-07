package errors.semantic_errors;

import identifier_objects.TYPE;
import org.antlr.v4.runtime.ParserRuleContext;

public class MismatchedTypes extends WaccSemanticError {

  private TYPE actual;
  private TYPE expected;

  public MismatchedTypes(ParserRuleContext ctx) {
    super(ctx);
  }

  public MismatchedTypes(ParserRuleContext ctx, String partOfCodeWithError) {
    super(ctx, partOfCodeWithError);
  }

  public MismatchedTypes(ParserRuleContext ctx, TYPE actual, TYPE expected) {
    super(ctx);
    this.actual = actual;
    this.expected = expected;
  }


  @Override
  public String getErrorMessage() {
    return "Expected value of type : " + expected.toString().toUpperCase() +
        ", but got type : " + actual.toString().toUpperCase();
  }
}
