package backend.labels;

import backend.instructions.Instruction;
import java.util.Arrays;
import java.util.List;

public class InstructionLabel {

  private final String name;
  private final List<Instruction> instructions;
  private boolean isLastFunction = false;

  public InstructionLabel(String name, List<Instruction> instructions) {
    this.name = name;
    this.instructions = instructions;
  }

  public InstructionLabel setLastFunction(){
    isLastFunction = true;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(name);
    builder.append(": \n");
    instructions.forEach(i -> builder.append("\t").append(i).append("\n"));
    if(isLastFunction) builder.append("\t.ltorg");
    return builder.toString();
  }
}
