package wacc.backend.instructions.stack_instructions;

import wacc.backend.registers.Register;

public class Push extends StackInstruction {

  public Push(Register rn) {
    super(rn);
  }

  @Override
  public String toString() {
    return "PUSH " + super.toString();
  }
}
