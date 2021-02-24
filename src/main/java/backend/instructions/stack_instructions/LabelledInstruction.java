package backend.instructions.stack_instructions;

import backend.instructions.Instruction;

public class LabelledInstruction extends Instruction {

  private final String label;
  private final Instruction instruction;

  public LabelledInstruction(String label, Instruction instruction) {
    this.label = label;
    this.instruction = instruction;
  }

  public String toString() {
    String str = "\b" + label + ":";
    if (instruction != null) {
      str += "\n\t" + instruction;
    }
    return str;
  }

}
