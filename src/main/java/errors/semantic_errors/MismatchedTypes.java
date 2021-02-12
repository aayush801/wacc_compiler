package errors.semantic_errors;

import identifier_objects.IDENTIFIER;
import org.antlr.v4.runtime.Token;

public class MismatchedTypes extends WaccSemanticError {

  private final IDENTIFIER actual, expected;
  boolean twoTypes = false;
  private IDENTIFIER expectedOther;


  public MismatchedTypes(Token token, IDENTIFIER actual, IDENTIFIER expected) {
    super(token);
    this.actual = actual;
    this.expected = expected;
  }

  public MismatchedTypes(Token token, IDENTIFIER actual, IDENTIFIER expected,
      IDENTIFIER expectedOther) {
    super(token);
    twoTypes = true;
    this.actual = actual;
    this.expected = expected;
    this.expectedOther = expectedOther;
  }


  @Override
  public String getErrorMessage() {
    if (!twoTypes) {
      return "Expected value of type : " + ((expected == null) ? "null"
          : expected.toString().toUpperCase()) +
          ", but got type : " + ((actual == null) ? "null" : actual.toString().toUpperCase());
    }
    return "Expected value of type : " + ((expected == null) ? "null"
        : expected.toString().toUpperCase()) + "or " + expectedOther.toString().toUpperCase() +
        ", but got type : " + ((actual == null) ? "null" : actual.toString().toUpperCase());
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
