package backend.operands;

public class ImmediateFloat extends Operand {

  private float value;

  public ImmediateFloat(float value) {
    this.value = value;
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }


  @Override
  public String toString() {
    return "#" + value;
  }
}
