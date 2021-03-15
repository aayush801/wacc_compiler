package wacc.backend.operands;

import java.util.Objects;

public class ImmediateNum extends Operand {

  public static final ImmediateNum ZERO = new ImmediateNum(0);
  public static final ImmediateNum ONE = new ImmediateNum(1);
  public static Integer MAX_SIZE = 1024;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImmediateNum that = (ImmediateNum) o;
    return Objects.equals(getValue(), that.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }
}
