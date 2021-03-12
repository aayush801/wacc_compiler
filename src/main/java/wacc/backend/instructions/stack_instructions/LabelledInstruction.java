package wacc.backend.instructions.stack_instructions;

import wacc.backend.instructions.Instruction;

public class LabelledInstruction extends Instruction {

  private static int INDEX = 0;
  private final String label;

  public LabelledInstruction() {
    label = "L" + INDEX++;
  }

  public String getLabel() {
    return label;
  }

  public String toString() {
    return label + ":";
  }

}
