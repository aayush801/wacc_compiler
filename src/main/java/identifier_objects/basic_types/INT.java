package identifier_objects.basic_types;

import identifier_objects.TYPE;
import identifier_objects.polymorhpic_types.COMPARABLE;

public class INT extends TYPE {
  public static String name = "int";
  protected final int min, max;

  public INT(int min, int max) {
    super(name);
    this.min = min;
    this.max = max;
  }

}
