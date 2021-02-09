package errors.semantic_errors;

import identifier_objects.IDENTIFIER;
import org.antlr.v4.runtime.ParserRuleContext;

public class MismatchedTypes extends WaccSemanticError {

  private IDENTIFIER actual;
  private IDENTIFIER expected;

  public MismatchedTypes(ParserRuleContext ctx) {
    super(ctx);
  }

  public MismatchedTypes(ParserRuleContext ctx, String partOfCodeWithError) {
    super(ctx, partOfCodeWithError);
  }


  public MismatchedTypes(ParserRuleContext ctx, IDENTIFIER actual, IDENTIFIER expected) {
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
