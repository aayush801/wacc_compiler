package backend.operands;

public class Immediate extends Operand {

  private Integer value;

  public static final Immediate ONE = new Immediate(1);
  public static final Immediate ZERO = new Immediate(0);

  public Immediate(Integer value) {
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
