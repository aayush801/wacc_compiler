package identifier_objects;

public class TYPE extends IDENTIFIER {

  public TYPE(String name) {
    super(name);
  }

  public boolean equals(Object o) {
    if (o instanceof TYPE) {
      TYPE type = (TYPE) o;
      return type.getName().equals(name);
    }
    return false;
  }
}
