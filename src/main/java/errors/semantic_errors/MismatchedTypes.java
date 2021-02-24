package errors.semantic_errors;

import frontend.identifier_objects.IDENTIFIER;
import org.antlr.v4.runtime.ParserRuleContext;

public class MismatchedTypes extends WaccSemanticError {

  private final IDENTIFIER actual, expected;
  boolean twoTypes = false;
  private IDENTIFIER expectedOther;


  public MismatchedTypes(ParserRuleContext ctx, IDENTIFIER actual, IDENTIFIER expected) {
    super(ctx);
    this.actual = actual;
    this.expected = expected;
  }

  public MismatchedTypes(ParserRuleContext ctx, IDENTIFIER actual, IDENTIFIER expected,
      IDENTIFIER expectedOther) {
    super(ctx);
    twoTypes = true;
    this.actual = actual;
    this.expected = expected;
    this.expectedOther = expectedOther;
  }


  @Override
  public String getErrorMessage() {
    String expectedMsg = (expected == null) ? "null" : expected.toString().toUpperCase();
    String actualMsg = (actual == null) ? "null" : actual.toString().toUpperCase();

    if (!twoTypes) {

      return "Expected value of type : "
          + expectedMsg
          + ", but got type : "
          + actualMsg;

    }

    return "Expected value of type : "
        + expectedMsg
        + " or "
        + expectedOther.toString().toUpperCase()
        + ", but got type : "
        + actualMsg;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof MismatchedTypes) {
      MismatchedTypes e = (MismatchedTypes) o;
      return actual.equals(e.actual) && expected.equals(e.expected);
    }
    return false;
  }

}
