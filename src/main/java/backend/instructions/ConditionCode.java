package backend.instructions;

public enum ConditionCode {
  EQ,
  NE,
  GT,
  LT,
  GE,
  LE,
  NONE;

  @Override
  public String toString() {
    switch (this) {
      case EQ:
        return "EQ";
      case NE:
        return "NE";
      case GT:
        return "GT";
      case LT:
        return "LT";
      case GE:
        return "GE";
      case LE:
        return "LE";
      default:
        return "";
    }
  }
}
