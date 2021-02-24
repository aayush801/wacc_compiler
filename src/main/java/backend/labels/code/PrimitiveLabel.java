package backend.labels.code;

import backend.instructions.Instruction;
import java.util.List;

public class PrimitiveLabel extends InstructionLabel {

  public PrimitiveLabel(String name, List<Instruction> instructions) {
    super("p_" + name, instructions);
  }
}
