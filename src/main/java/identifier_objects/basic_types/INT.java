package identifier_objects.basic_types;

import identifier_objects.intermediate_types.EXPR;

public class INT extends EXPR {

  public static String name = "int";
  protected final int min, max;

  public INT(int min, int max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public String toString() {
    return name;
  }
}
