package identifier_objects.basic_types;

import identifier_objects.intermediate_types.EXPR;

public class CHAR extends EXPR {

  public static String name = "char";


  protected final int min, max;

  public CHAR(int min, int max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public String toString() {
    return name;
  }
}
