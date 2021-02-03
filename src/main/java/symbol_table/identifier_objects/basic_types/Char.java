package symbol_table.identifier_objects.basic_types;

import symbol_table.identifier_objects.Type;

public class Char extends Type {

  protected final int min, max;

  public Char(int min, int max) {
    super("char");
    this.min = min;
    this.max = max;
  }
}
