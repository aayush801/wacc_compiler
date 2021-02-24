package backend.registers;

import backend.operands.Operand;

enum RegType {
  ADDRESS,
  IMMEDIATE
}

public class Register extends Operand {

  public static final Register R0 = new Register(0);
  public static final Register R1 = new Register(1);
  private final Integer number;
  private Integer value;
  private RegType type;

  public Register(Integer number) {
    this.number = number;
  }

  public void setValue(Integer value, RegType type) {
    this.value = value;
    this.type = type;
  }

  public Integer getNumber() {
    return number;
  }

  public Integer getValue() {
    return value;
  }

  public RegType getType() {
    return type;
  }

  @Override
  public String toString() {
    return "r" + number;
  }
}
