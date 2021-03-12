package wacc.backend.primitive_functions;

import wacc.backend.ProgramGenerator;
import wacc.backend.instructions.Branch;
import wacc.backend.instructions.Instruction;
import wacc.backend.instructions.Move;
import wacc.backend.labels.code.PrimitiveLabel;
import wacc.backend.operands.ImmediateNum;
import wacc.backend.registers.Register;
import java.util.ArrayList;
import java.util.List;

public class RuntimeError {

  public static PrimitiveLabel printRuntimeErrorCheck(ProgramGenerator program) {
    List<Instruction> ret = new ArrayList<>();

    // add p_print_string if necessary.
    PrimitiveLabel printLabel = PrintFunctions.printString(program);
    program.addPrimitive(printLabel);

    // BL p_print_string
    ret.add(new Branch(printLabel.getLabelName(), true));

    // MOV r0, #-1
    ret.add(new Move(Register.R0, new ImmediateNum(-1)));

    // BL exit
    ret.add(new Branch("exit", true));

    return new PrimitiveLabel("throw_runtime_error", ret, program);
  }

}
