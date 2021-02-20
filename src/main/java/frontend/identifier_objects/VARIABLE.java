package frontend.identifier_objects;


public class VARIABLE extends IDENTIFIER {

  TYPE type;
  private int offset;

  public VARIABLE(TYPE type) {
    super("variable");
    this.type = type;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public Integer getOffset() {
    return offset;
  }

  public TYPE getType() {
    return type;
  }

}
