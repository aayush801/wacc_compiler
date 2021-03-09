package backend.instructions;

import backend.operands.Operand;
import backend.registers.Register;

public class Move extends Instruction {

  protected final Register Rd;
  protected final Operand Operand;
  private boolean isNegated = false;

  public Move(Register Rd, Operand Operand) {
    this.Rd = Rd;
    this.Operand = Operand;
  }

  public Move(Register Rd, Operand Operand, boolean isNegated) {
    this.Rd = Rd;
    this.Operand = Operand;
    this.isNegated = isNegated;
  }

  public Move(ConditionCode code, Register Rd, Operand Operand,
      boolean flags) {
    super(code);
    this.Rd = Rd;
    this.Operand = Operand;
    setFlags(flags);
  }

  public boolean isNegated() {
    return isNegated;
  }

  public Register getRd() {
    return Rd;
  }

  public backend.operands.Operand getOperand() {
    return Operand;
  }


  @Override
  public String toString() {
    return ((isNegated) ? "MVN" : "MOV") + getFlags() + " " + Rd + ", " + Operand;
  }
}
