package backend.labels.code;

import backend.instructions.Instruction;
import backend.labels.Label;
import java.util.List;

public class InstructionLabel extends Label {

  private final List<Instruction> instructions;
  private boolean isLastFunction = false;

  public InstructionLabel(String name, List<Instruction> instructions) {
    super(name);
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
    if(isLastFunction) builder.append("\t .ltorg\n");
    return builder.toString();
  }
}
