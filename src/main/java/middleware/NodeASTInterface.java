package middleware;

import backend.instructions.Instruction;
import backend.registers.Register;
import java.util.List;

public interface NodeASTInterface {
  void check();
  List<Instruction> translate(List<Register> registers);
}
