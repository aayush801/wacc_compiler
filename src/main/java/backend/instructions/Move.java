package backend.instructions;

import backend.operands.Operand;
import backend.registers.Register;

public class Move extends Instruction {

  private final Register Rd;
  private final Operand Operand;

  public Move(Register Rd, Operand Operand) {
    super();
    this.Rd = Rd;
    this.Operand = Operand;
  }

  public Move(ConditionCode code, Register Rd, Operand Operand,
      boolean flags) {
    super(code);
    this.Rd = Rd;
    this.Operand = Operand;
    setFlags(flags);
  }

  public Register getRd() {
    return Rd;
  }

  public backend.operands.Operand getOperand() {
    return Operand;
  }


  @Override
  public String toString() {
    return "MOV" + getFlags() + " " + Rd + ", " + Operand;
  }
}
