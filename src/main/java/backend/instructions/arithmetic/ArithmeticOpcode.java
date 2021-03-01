package backend.instructions.arithmetic;

public enum ArithmeticOpcode {
  ADD,
  SMULL,
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
      case SMULL:
        return "SMULL";
      case AND:
        return "AND";
      case OR:
        return "ORR";
      case RSB:
        return "RSB";
      case EOR:
        return "EOR";
      default:
        return "";
    }
  }
}

