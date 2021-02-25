package backend.labels.code;

import backend.ProgramGenerator;
import backend.instructions.Instruction;
import java.util.List;

public class PrimitiveLabel extends InstructionLabel {

  public PrimitiveLabel(String name, List<Instruction> instructions, ProgramGenerator program) {
    super("p_" + name, instructions);
    program.pushLR(instructions);
    program.popPC(instructions);
  }
}
