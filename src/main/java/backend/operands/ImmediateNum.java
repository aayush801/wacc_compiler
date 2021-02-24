package backend.operands;

public class ImmediateNum extends Operand {

  private Integer value;

  public ImmediateNum(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }


  @Override
  public String toString() {
    return "#" + value;
  }
}
