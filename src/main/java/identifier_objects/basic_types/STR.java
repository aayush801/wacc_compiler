package identifier_objects.basic_types;

import identifier_objects.intermediate_types.EXPR;

public class STR extends EXPR {

  public static String name = "string";

  @Override
  public String toString() {
    return name;
  }
}
