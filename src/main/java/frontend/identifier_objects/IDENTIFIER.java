package frontend.identifier_objects;

public abstract class IDENTIFIER {

  protected String name;

  public IDENTIFIER(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof IDENTIFIER) {
      IDENTIFIER id = (IDENTIFIER) o;
      return id.getName().equals(name);
    }
    return false;
  }
}
