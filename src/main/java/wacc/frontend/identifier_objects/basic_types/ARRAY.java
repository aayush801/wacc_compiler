package wacc.frontend.identifier_objects.basic_types;

import wacc.frontend.identifier_objects.TYPE;

public class ARRAY extends TYPE {

  public static String name = "array";

  protected final TYPE elementType;

  public ARRAY(TYPE elementType) {
    super(name);
    this.elementType = elementType;
  }

  public TYPE getType() {
    return elementType;
  }

  @Override
  public String toString() {
    return elementType.toString() + "[]";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ARRAY) {
      ARRAY arr = (ARRAY) o;
      return elementType.equals(arr.elementType);
    }
    return false;
  }

  @Override
  public int getSize() {
    return 4;
  }
}
