package identifier_objects.polymorhpic_types;

import identifier_objects.basic_types.ARRAY;
import identifier_objects.basic_types.PAIR;

public class FREEABLE extends EXPR {

  @Override
  public String toString() {
      return PAIR.name + " or " + ARRAY.name;
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof PAIR || o instanceof ARRAY);
  }
}
