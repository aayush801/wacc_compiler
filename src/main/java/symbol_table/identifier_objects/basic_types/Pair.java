package symbol_table.identifier_objects.basic_types;

import symbol_table.identifier_objects.Type;

public class Pair extends Type {

  protected Type first, second;

  public Pair(Type first, Type second) {
    super("pair");
    this.first = first;
    this.second = second;
  }

  public Pair() {
    this(null, null);
  }

  public Pair setTypes(Type first, Type second) {
    return new Pair(first, second);
  }
}
