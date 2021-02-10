package identifier_objects.polymorhpic_types;

import identifier_objects.TYPE;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;

public class EXPR extends TYPE {

  public EXPR() {
    super(null);
  }

  @Override
  public String toString() {
    return "EXPR";
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof TYPE;
  }
}
