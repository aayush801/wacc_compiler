package backend.registers;

public class StackPointer extends Register {

  public StackPointer() {
    super(13);
  }

  @Override
  public String toString() {
    return "sp";
  }
}
