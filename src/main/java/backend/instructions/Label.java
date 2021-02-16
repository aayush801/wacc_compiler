package backend.instructions;

import java.util.Arrays;
import java.util.List;

public class Label extends Instruction {

  private final String name;
  private final List<Instruction> instructions;

  public Label(String name, List<Instruction> instructions) {
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
