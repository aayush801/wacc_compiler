package backend.instructions.amodes;

import backend.registers.Register;

public class RegisterOffset extends AddressingMode{
  private final Register Rn;
  private final Register Rm;
  private final boolean sign;

  public RegisterOffset(Register Rn, Register Rm, boolean sign) {
    this.Rn = Rn;
    this.Rm = Rm;
    this.sign = sign;
  }

  public String getSign(){
    return sign ? "+" : "-";
  }

  @Override
  public String toString() {
    return "[ " + Rn + ", " + getSign() + Rm + " ]";
  }
}
