package frontend.identifier_objects;

public class POINTER extends TYPE {

  private final TYPE type;

  public POINTER(TYPE type) {
    super("pointer");
    this.type = type;
  }

  @Override
  public String toString() {
    return "pointer ("+type+")";
  }

  @Override
  public int getSize() {
    return 4;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof POINTER) {
      return type.equals(((POINTER) o).type);
    }
    return false;
  }
}
