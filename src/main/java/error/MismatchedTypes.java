package error;

import identifier_objects.TYPE;

public class MismatchedTypes extends WaccError {

  protected TYPE expected, actual;

  public MismatchedTypes(TYPE expected, TYPE actual) {
    super("MismatchedTypes");
    this.expected = expected;
    this.actual = actual;
  }

  @Override
  public String toString() {
    return "Expected value of type : " + expected.toString().toUpperCase() +
        ", but got type : " + actual.toString().toUpperCase();
  }
}
