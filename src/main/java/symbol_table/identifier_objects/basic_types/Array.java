package symbol_table.identifier_objects.basic_types;

import symbol_table.identifier_objects.Type;

public class Array extends Type {

  protected final Type elementType;
  protected final int length;

  public Array(Type elementType, int length) {
    super("array");
    this.elementType = elementType;
    this.length = length;
  }
}
