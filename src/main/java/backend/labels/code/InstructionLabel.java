package backend.labels.code;

import backend.instructions.Instruction;
import backend.instructions.stack_instructions.Pop;
import backend.instructions.stack_instructions.Push;
import backend.labels.Label;
import backend.registers.LinkRegister;
import backend.registers.ProgramCounter;
import java.util.List;

public class InstructionLabel extends Label {

  private final List<Instruction> instructions;

  public InstructionLabel(String name, List<Instruction> instructions) {
    super(name);
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
