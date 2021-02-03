package symbol_table.identifier_objects.basic_types;

import symbol_table.identifier_objects.Type;

public class Int extends Type {

  protected final int min, max;

  public Int(int min, int max) {
    super("int");
    this.min = min;
    this.max = max;
  }
}
