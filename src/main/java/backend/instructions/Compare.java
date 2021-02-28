package backend.instructions;

import backend.operands.Operand;
import backend.registers.Register;

public class Compare extends Instruction {

  private final Register Rn;
  private final Operand operand;

  public Compare(Register Rn, Operand operand) {
    super();
    this.Rn = Rn;
    this.operand = operand;
  }

  public String toString() {
    return "CMP" + getFlags() + " " + Rn + ", " + operand;
  }
}
