package wacc.backend.instructions.stack_instructions;

import wacc.backend.registers.Register;

public class Pop extends StackInstruction {

  public Pop(Register rn) {
    super(rn);
  }

  @Override
  public String toString() {
    return "POP " + super.toString();
  }
}
