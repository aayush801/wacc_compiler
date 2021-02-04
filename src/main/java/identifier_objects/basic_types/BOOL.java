package identifier_objects.basic_types;

import identifier_objects.intermediate_types.EXPR;

public class BOOL extends EXPR {

  public static String name = "bool";


  @Override
  public String toString() {
    return name;
  }
}
