package identifier_objects.basic_types;

import identifier_objects.TYPE;
import identifier_objects.polymorhpic_types.EQUATABLE;

public class PAIR extends EQUATABLE {
  public static String name = "pair";
  protected final TYPE first, second;

  public PAIR(TYPE first, TYPE second) {
    super(name);
    this.first = first;
    this.second = second;
  }

  public PAIR() {
    this(null, null);
  }

  public boolean equals(Object o) {
    if (o instanceof PAIR) {
      PAIR pair = (PAIR) o;
      return pair.first.equals(first) && pair.second.equals(second);
    }
    return false;
  }
}
