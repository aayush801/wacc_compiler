package identifier_objects.basic_types;

import identifier_objects.TYPE;

public class ARRAY extends TYPE {

  protected final TYPE elementType;

  public ARRAY(TYPE elementType) {
    super("array");
    this.elementType = elementType;
  }

  public ARRAY() {
    this(null);
  }
  public ARRAY setElementType(TYPE type) {
    return new ARRAY(type);
  }

  @Override
  public String toString(){
    return elementType + "[]";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ARRAY) {
      ARRAY arr = (ARRAY) o;
      return elementType.equals(arr.elementType);
    }
    return false;
  }
}
