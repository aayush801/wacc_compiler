package backend.instructions.stack_instructions;

import backend.instructions.Instruction;
import backend.registers.Register;


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
