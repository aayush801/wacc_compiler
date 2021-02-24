package frontend.identifier_objects;


public class VARIABLE extends IDENTIFIER {

  TYPE type;
  private int stackAddress;

  public VARIABLE(TYPE type) {
    super("variable");
    this.type = type;
  }

  public void setStackAddress(int stackAddress) {
    this.stackAddress = stackAddress;
  }

  public Integer getStackAddress() {
    return stackAddress;
  }

  public TYPE getType() {
    return type;
  }

}
