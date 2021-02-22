package backend.instructions.arithmetic;

public enum ArithmeticOpcode {
  ADD,
  MUL,
  SUB,
  AND,
  OR,
  EOR,
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
      case EOR:
        return "EOR";
      default:
        return "";
    }
  }
}

