package wacc.backend.instructions;

public abstract class Instruction {

  protected final ConditionCode code;
  protected boolean flags = false;

  protected Instruction(ConditionCode code) {
    this.code = code;
  }

  protected Instruction() {
    this(ConditionCode.NONE);
  }

  public String getFlags() {

    return (flags ? "S" : "") + code;

  }

  public void setFlags(boolean value) {
    flags = value;
  }
}
