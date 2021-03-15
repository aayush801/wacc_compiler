package wacc.backend.registers;

import wacc.backend.operands.Operand;


public class Register extends Operand {

  public static final Register
      R0 = new Register(0),
      R1 = new Register(1),
      R2 = new Register(2);
  private final Integer number;

  public Register(Integer number) {
    this.number = number;
  }

  public Integer getNumber() {
    return number;
  }

  @Override
  public String toString() {
    return "r" + number;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Register && number == ((Register) o).number;
  }
}
