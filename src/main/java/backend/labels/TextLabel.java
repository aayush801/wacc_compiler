package backend.labels;

import backend.instructions.Instruction;
import java.util.List;

public class TextLabel extends Instruction {

  private final String name;
  private final List<Instruction> instructions;

  public TextLabel(String name, List<Instruction> instructions) {
    this.name = name;
    this.instructions = instructions;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(name);
    builder.append(": \n");
    instructions.forEach(i -> builder.append("\t").append(i).append("\n"));
    return builder.toString();
  }
}
