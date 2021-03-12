package wacc.backend.instructions.stack_instructions;

import wacc.backend.instructions.Instruction;
import wacc.backend.registers.Register;


public abstract class StackInstruction extends Instruction {

  private final Register Rn;

  public StackInstruction(Register rn) {
    Rn = rn;
  }

  @Override
  public String toString() {
    return "{" + Rn + "}";
  }
}
