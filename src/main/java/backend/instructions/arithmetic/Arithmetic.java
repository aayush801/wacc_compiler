package backend.instructions.arithmetic;

import backend.instructions.Instruction;
import backend.operands.ImmediateChar;
import backend.operands.Operand;
import backend.registers.Register;

public class Arithmetic extends Instruction {

  private final Register Rd, Rn;
  private final backend.operands.Operand operand;
  private final ArithmeticOpcode opcode;

  public Arithmetic(ArithmeticOpcode opcode, Register Rd, Register Rn,
      Operand operand, boolean setConditionCodes) {
    this.Rd = Rd;
    this.Rn = Rn;
    this.operand = operand;
    this.opcode = opcode;
    flags = setConditionCodes;
  }

//  public Arithmetic(ArithmeticOpcode Opcode, Register Rd, Register Rn,
//      Operand Operand, boolean flags) {
//    this.Rd = Rd;
//    this.Rn = Rn;
//    this.Operand = Operand;
//    this.Opcode = Opcode;
//    setFlags(flags);
//  }

  @Override
  public String toString() {
    return opcode + (flags ? "S" : "") + " " + Rd + ", " + Rn + ", " + operand;
  }
}
