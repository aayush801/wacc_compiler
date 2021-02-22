package backend.instructions.arithmetic;

import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.operands.Operand;
import backend.registers.Register;

public class Arithmetic extends Instruction {

  private final Register Rd, Rn;
  private final backend.operands.Operand Operand;
  private final ArithmeticOpcode Opcode;

  public Arithmetic(ArithmeticOpcode Opcode, Register Rd, Register Rn,
      Operand Operand) {
    this.Rd = Rd;
    this.Rn = Rn;
    this.Operand = Operand;
    this.Opcode = Opcode;
  }

  public Arithmetic(ArithmeticOpcode Opcode, Register Rd, Register Rn,
      Operand Operand, boolean flags) {
    this.Rd = Rd;
    this.Rn = Rn;
    this.Operand = Operand;
    this.Opcode = Opcode;
    setFlags(flags);
  }

  @Override
  public String toString() {
    return Opcode + getFLags() + " " + Rd + ", " + Rn + ", " + Operand;
  }
}
