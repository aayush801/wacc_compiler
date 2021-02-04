package identifier_objects.basic_types;

import identifier_objects.polymorhpic_types.COMPARABLE;

public class CHAR extends COMPARABLE {
  public static String name = "char";
  protected final int min, max;

  public CHAR(int min, int max) {
    super(name);
    this.min = min;
    this.max = max;
  }
}
