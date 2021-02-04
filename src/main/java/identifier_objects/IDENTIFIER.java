package identifier_objects;

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
}
