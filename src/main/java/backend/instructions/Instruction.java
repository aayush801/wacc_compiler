package backend.instructions;

import backend.instructions.addr_modes.AddressingMode;
import backend.registers.Register;

public abstract class Instruction {

  private boolean flags = false;
  private final ConditionCode code;

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
