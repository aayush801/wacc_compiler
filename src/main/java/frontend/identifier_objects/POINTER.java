package frontend.identifier_objects;

public class POINTER extends TYPE {

  private final TYPE type;
  private final int level;

  public POINTER(TYPE type, int level) {
    this.type = type;
    this.level = level;
  }

  @Override
  public int getSize() {
    return 4;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof POINTER) {
      return type.equals(((POINTER) o).type) && level == ((POINTER) o).level;
    }
    return false;
  }
}
