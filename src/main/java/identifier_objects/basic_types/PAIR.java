package identifier_objects.basic_types;

import identifier_objects.TYPE;
import identifier_objects.intermediate_types.EXPR;

public class PAIR extends EXPR {

  public static String name = "pair";

  protected TYPE first, second;

  public PAIR(TYPE first, TYPE second) {
    this.first = first;
    this.second = second;
  }

  public PAIR() {
    this(null, null);
  }


  @Override
  public String toString() {
    return name;
  }
}
