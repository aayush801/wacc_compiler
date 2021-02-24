package frontend.identifier_objects;


public class PARAM extends IDENTIFIER {

  private final TYPE type;
  private int offset;

  public PARAM(TYPE type) {
    super("param");
    this.type = type;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public String toString() {
    return name;
  }

}
