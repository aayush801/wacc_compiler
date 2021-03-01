package backend.instructions.arithmetic;

import backend.instructions.Instruction;
import backend.operands.ImmediateChar;
import backend.operands.Operand;
import backend.registers.Register;

public class Arithmetic extends Instruction {

  private final Register Rd, Rn;
  private final backend.operands.Operand operand;
  private final ArithmeticOpcode opcode;
  private boolean registerSave = false;

  public Arithmetic(ArithmeticOpcode opcode, Register Rd, Register Rn,
      Operand operand, boolean setConditionCodes) {
    this.Rd = Rd;
    this.Rn = Rn;
    this.operand = operand;
    this.opcode = opcode;
    flags = setConditionCodes;
  }

  public Arithmetic(ArithmeticOpcode opcode, Register Rd, Register Rn,
      Operand operand, boolean setConditionCodes, boolean registerSave) {
    this(opcode, Rd, Rn, operand, setConditionCodes);
    this.registerSave = registerSave;
  }

  @Override
  public String toString() {
    return opcode + (flags ? "S" : "") + " " +
            (!registerSave ? Rd + ", " + Rn + ", " + operand :
                Rd + ", " + operand + ", " + Rn);
  }
}
