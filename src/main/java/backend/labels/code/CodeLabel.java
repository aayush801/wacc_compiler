package backend.labels.code;

import backend.instructions.Instruction;
import backend.instructions.stack_instructions.LabelledInstruction;
import backend.labels.Label;
import java.util.List;

public class CodeLabel extends Label {

  protected final List<Instruction> instructions;

  public CodeLabel(String name, List<Instruction> instructions) {
    super(name);
    this.instructions = instructions;
  }


  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(name);
    builder.append(": \n");
    instructions.forEach(i -> {
      if (!(i instanceof LabelledInstruction)) {
        builder.append("\t");
      }
      builder.append(i).append("\n");
      }
    );
    return builder.toString();
  }
}
