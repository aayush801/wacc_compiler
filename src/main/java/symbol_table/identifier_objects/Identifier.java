package symbol_table.identifier_objects;

public abstract class Identifier {

  protected String name;

  public Identifier(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
