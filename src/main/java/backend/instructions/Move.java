package backend.instructions;

import backend.operands.Operand;
import backend.registers.Register;

public class Move extends Instruction {

  private final Register Rd;
  private final Operand Operand;

  public Move(Register Rd, Operand Operand) {
    this.Rd = Rd;
    this.Operand = Operand;
  }

  public Move(Register Rd, Operand Operand, boolean flags) {
    this(Rd, Operand);
    setFlags(flags);
  }

  @Override
  public String toString() {
    return "MOV" + getFLags() + " " + Rd + ", " + Operand;
  }
}
