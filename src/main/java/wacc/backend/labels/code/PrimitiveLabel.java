package wacc.backend.labels.code;

import wacc.backend.ProgramGenerator;
import wacc.backend.instructions.Instruction;
import wacc.backend.instructions.stack_instructions.Pop;
import wacc.backend.instructions.stack_instructions.Push;
import java.util.List;

public class PrimitiveLabel extends CodeLabel {

  private final ProgramGenerator program;

  public PrimitiveLabel(String name, List<Instruction> instructions,
      ProgramGenerator program) {
    super("p_" + name, instructions);
    this.program = program;
  }

  public PrimitiveLabel wrap() {

    instructions.add(0, new Push(program.LR));

    instructions.add(new Pop(program.PC));

    return this;
  }
}
