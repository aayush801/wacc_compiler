package backend.instructions.arithmetic;

import backend.operands.Operand;
import backend.registers.Register;

public enum ArithmeticOpcode {
  ADD,
  MUL,
  SUB,
  AND,
  OR,
  RSB;

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
      case RSB:
        return "RSB";
      default:
        return "";
    }
  }
}

