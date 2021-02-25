package backend.instructions.stack_instructions;

import backend.instructions.Instruction;

public class LabelledInstruction extends Instruction {
  private static int index = 0;
  private final String label;

  public LabelledInstruction(){
    label = "L" + index++;
  }

  public String getLabel() {
    return label;
  }

  public String toString() {
    return "\b" + label + ": \t";
  }

}
