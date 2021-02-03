package symbol_table.identifier_objects;

public class Type extends Identifier {

  public Type(String name) {
    super(name);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Type) {
      return this.name.equals(((Type) obj).getName());
    }
    return false;
  }
}
