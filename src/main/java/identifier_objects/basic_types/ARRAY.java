package identifier_objects.basic_types;

import identifier_objects.TYPE;
import identifier_objects.polymorhpic_types.EQUATABLE;

public class ARRAY extends EQUATABLE {
  public static String name = "array";

  protected final TYPE elementType;

  public ARRAY(TYPE elementType) {
    super(name);
    this.elementType = elementType;
  }

  public TYPE getType(){
    return elementType;
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
