package frontend.identifier_objects;

public class TYPE extends IDENTIFIER {

  public TYPE(String name) {
    super(name);
  }

  public TYPE() {
    super(null);
  }

  @Override
  public String toString() {
    return name == null ? "EXPR" : name;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof TYPE;
  }

  public int getSize(){
    throw new Error("ovveride method in TYPE");
  }
}
