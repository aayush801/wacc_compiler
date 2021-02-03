package symbol_table.identifier_objects;

public class Variable extends Identifier {

  Type type;

  public Variable(String name, Type type) {
    super(name);
    this.type = type;
  }
}
