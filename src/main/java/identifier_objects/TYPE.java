package identifier_objects;

public abstract class TYPE extends IDENTIFIER {

  public TYPE(String name) {
    super(name);
  }
  public TYPE getType(){
    return this;
  }

  public boolean equals(Object o) {
    if (o instanceof TYPE) {
      TYPE type = (TYPE) o;
      return type.getName().equals(name);
    }
    return false;
  }
}
