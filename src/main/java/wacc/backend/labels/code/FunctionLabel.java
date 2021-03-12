package wacc.backend.labels.code;

import wacc.backend.instructions.Instruction;
import java.util.List;

public class FunctionLabel extends CodeLabel {

  public FunctionLabel(String name, List<Instruction> instructions) {

    super("f_" + name, instructions);

  }

}
