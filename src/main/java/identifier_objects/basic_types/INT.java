package identifier_objects.basic_types;

import identifier_objects.TYPE;

public class INT extends TYPE {

  protected final int min, max;

  public INT(int min, int max) {
    super("int");
    this.min = min;
    this.max = max;
  }


}
