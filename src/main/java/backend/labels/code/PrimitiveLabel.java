package backend.labels.code;

import backend.ProgramGenerator;
import backend.instructions.Instruction;
import java.util.List;

public class PrimitiveLabel extends CodeLabel {

  private final ProgramGenerator program;

  public PrimitiveLabel(String name, List<Instruction> instructions, ProgramGenerator program) {
    super("p_" + name, instructions);
    this.program = program;
  }

  public PrimitiveLabel wrap() {
    program.pushLR(instructions);
    program.popPC(instructions);
    return this;
  }
}
