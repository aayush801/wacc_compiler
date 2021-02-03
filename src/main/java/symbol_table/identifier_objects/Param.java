package symbol_table.identifier_objects;

public class Param extends Identifier {

  protected Type type;

  public Param(String name, Type type) {
    super(name);
    this.type = type;
  }
}
