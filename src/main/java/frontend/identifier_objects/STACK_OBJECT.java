package frontend.identifier_objects;

public abstract class STACK_OBJECT extends IDENTIFIER {

  TYPE type;
  private int stackAddress;

  public STACK_OBJECT(String name, TYPE type) {
    super(name);
    this.type = type;
  }

  public Integer getStackAddress() {
    return stackAddress;
  }

  public void setStackAddress(int stackAddress) {
    this.stackAddress = stackAddress;
  }

  public TYPE getType() {
    return type;
  }
}
