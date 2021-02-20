package backend.instructions;

import backend.registers.Register;

public class Compare extends Instruction {

  private final Register Rn, Rm;

  public Compare(Register Rn, Register Rm) {
    super();
    this.Rn = Rn;
    this.Rm = Rm;
  }

  public String toString() {
    return "CMP" + getFLags() + " " + Rn + ", " + Rm;
  }
}
