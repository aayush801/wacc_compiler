package backend.operands;

public class ImmediateNum extends Operand {

  private Integer value;

  public static Integer MAX_SIZE = 1024;

  public static final ImmediateNum ZERO = new ImmediateNum(0);
  public static final ImmediateNum ONE = new ImmediateNum(1);

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
