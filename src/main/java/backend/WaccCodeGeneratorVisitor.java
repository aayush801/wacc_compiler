package backend;

import backend.instructions.EOC;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.addr_modes.Address;
import backend.labels.code.CodeLabel;
import backend.registers.Register;
import java.util.ArrayList;
import java.util.List;
import middleware.NodeAST;
import middleware.NodeASTList;
import middleware.ProgAST;
import middleware.function_ast.FunctionDeclarationAST;

public class WaccCodeGeneratorVisitor {

  public List<Instruction> visit(NodeAST prog) {
    return null;
  }

  public List<Instruction> visit(ProgAST prog, List<Register> registers) {
    return null;
  }
}
