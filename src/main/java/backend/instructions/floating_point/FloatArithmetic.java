package backend.instructions.floating_point;

import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.operands.Operand;
import backend.registers.Register;

public class FloatArithmetic extends Arithmetic {

  public FloatArithmetic(ArithmeticOpcode opcode,
      Register Rd, Register Rn, Operand operand,
      Operand operand1, boolean registerSave) {
    super(opcode, Rd, Rn, operand, operand1, registerSave);
  }

  public FloatArithmetic(ArithmeticOpcode opcode, Register Rd, Register Rn,
      Operand operand, boolean setConditionCodes) {
    super(opcode, Rd, Rn, operand, setConditionCodes);
  }

  public FloatArithmetic(ArithmeticOpcode opcode, Register Rd, Register Rn,
      Operand operand, boolean setConditionCodes, boolean registerSave) {
    super(opcode, Rd, Rn, operand, setConditionCodes, registerSave);
  }

  @Override
  public String toString() {
    return "V" + getOpcode() + getFlags() + ".F32"  + super.operandString();
  }
}
