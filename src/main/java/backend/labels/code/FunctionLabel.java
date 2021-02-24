package backend.labels.code;

import backend.instructions.Instruction;
import backend.instructions.stack_instructions.Pop;
import backend.instructions.stack_instructions.Push;
import backend.registers.LinkRegister;
import backend.registers.ProgramCounter;
import java.util.List;

public class FunctionLabel extends InstructionLabel{

  public FunctionLabel(String name, List<Instruction> instructions) {
    super("f_"+name, instructions);
  }

}
