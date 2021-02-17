package backend.instructions.stack_instructions;

import backend.registers.Register;

public class Push extends StackInstruction {

  public Push(Register rn) {
    super(rn);
  }

  @Override
  public String toString() {
    return "PUSH " + super.toString();
  }
}
