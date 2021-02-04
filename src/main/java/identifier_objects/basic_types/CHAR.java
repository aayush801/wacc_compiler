package identifier_objects.basic_types;

import identifier_objects.TYPE;

public class CHAR extends TYPE {

  protected final int min, max;

  public CHAR(int min, int max) {
    super("char");
    this.min = min;
    this.max = max;
  }
}
