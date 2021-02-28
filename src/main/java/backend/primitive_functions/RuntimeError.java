package backend.primitive_functions;

import backend.ProgramGenerator;
import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Move;
import backend.labels.code.PrimitiveLabel;
import backend.operands.ImmediateNum;
import backend.registers.Register;
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
    ret.add(new Move(new Register(0), new ImmediateNum(-1)));

    // BL exit
    ret.add(new Branch("exit", true));

    return new PrimitiveLabel("throw_runtime_error", ret, program);
  }

}
