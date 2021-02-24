package backend.instructions;

public abstract class Instruction {

  protected final ConditionCode code;
  protected boolean flags = false;

  protected Instruction(ConditionCode code) {
    this.code = code;
  }

  protected Instruction() {
    this(ConditionCode.NONE);
  }

  public void setFlags(boolean value) {
    flags = value;
  }

  public String getFLags() {
    return (flags ? "S" : "") + code;
  }
}
