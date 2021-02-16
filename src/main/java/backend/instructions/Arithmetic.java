package backend.instructions;

import backend.operands.Operand;
import backend.registers.Register;

enum Opcode {
  ADD,
  MUL,
  SUB,
  AND,
  OR;

  @Override
  public String toString() {
    switch (this) {
      case ADD:
        return "ADD";
      case SUB:
        return "SUB";
      case MUL:
        return "MUL";
      case AND:
        return "AND";
      case OR:
        return "OR";
      default:
        return "";
    }
  }
}

public class Arithmetic extends Instruction {

  private final Register Rd, Rn;
  private final Operand Operand;

  private final Opcode Opcode;

  public Arithmetic(Opcode Opcode, Register Rd, Register Rn, Operand Operand) {
    this.Rd = Rd;
    this.Rn = Rn;
    this.Operand = Operand;
    this.Opcode = Opcode;
  }

  public Arithmetic(Opcode Opcode, Register Rd, Register Rn, Operand Operand, boolean flags) {
    this(Opcode, Rd, Rn, Operand);
    setFlags(flags);
  }

  @Override
  public String toString() {
    return Opcode + getFLags() + " " + Rd + ", " + Rn + ", " + Operand;
  }
}
