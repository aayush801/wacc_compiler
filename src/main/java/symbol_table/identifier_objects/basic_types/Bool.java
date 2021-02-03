package symbol_table.identifier_objects.basic_types;

import symbol_table.identifier_objects.Type;

public class Bool extends Type {

  protected final int min, max;

  public Bool(int min, int max) {
    super("bool");
    this.min = min;
    this.max = max;
  }
}
