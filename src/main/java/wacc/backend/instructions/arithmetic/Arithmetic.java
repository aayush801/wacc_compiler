package wacc.backend.instructions.arithmetic;

import wacc.backend.instructions.Instruction;
import wacc.backend.operands.Operand;
import wacc.backend.registers.Register;

public class Arithmetic extends Instruction {

  private final Register Rd, Rn;
  private final Operand operand;


  private final ArithmeticOpcode opcode;
  private wacc.backend.operands.Operand operand1;
  private boolean registerSave = false;

  public Arithmetic(ArithmeticOpcode opcode, Register Rd, Register Rn,
      Operand operand, Operand operand1, boolean registerSave) {
    this.opcode = opcode;
    this.Rd = Rd;
    this.Rn = Rn;
    this.operand = operand;
    this.operand1 = operand1;
    this.registerSave = registerSave;
  }

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


  public Register getRd() {
    return Rd;
  }

  public Register getRn() {
    return Rn;
  }

  public Operand getOperand() {
    return operand;
  }

  public ArithmeticOpcode getOpcode() {
    return opcode;
  }

  @Override
  public String toString() {

    return opcode + (flags ? "S" : "") + " " + operandString();
  }

  protected String operandString() {
    boolean operand1Exists = operand1 != null;
    return !registerSave ?
        Rd + ", " + Rn + ", " + operand +
            (operand1Exists ? ", " + operand1 : "") :
        (operand1Exists ?
            Rd + ", " + Rn + ", " + operand1 + ", " + operand :
            Rd + ", " + operand + ", " + Rn);
  }
}
