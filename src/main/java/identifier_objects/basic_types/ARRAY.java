package identifier_objects.basic_types;

import identifier_objects.TYPE;

public class ARRAY extends TYPE {

  public static String name = "array";

  protected final TYPE elementType;
  protected final int length;

  public ARRAY(TYPE elementType, int length) {
    this.elementType = elementType;
    this.length = length;
  }

  @Override
  public String toString() {
    return name;
  }

}
