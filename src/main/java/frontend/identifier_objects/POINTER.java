package frontend.identifier_objects;

public class POINTER extends TYPE {

  private TYPE type;

  public POINTER(TYPE type) {
    this.type = type;
  }

  @Override
  public int getSize() {
    return 4;
  }
}
