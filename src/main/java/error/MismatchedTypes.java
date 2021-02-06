package error;

import identifier_objects.TYPE;

public class MismatchedTypes extends WaccError {

  public MismatchedTypes(TYPE expected, TYPE actual) {
    super("Expected value of type : " + expected.toString().toUpperCase() +
        ", but got type : " + actual.toString().toUpperCase());
  }
}
