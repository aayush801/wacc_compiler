package wacc.backend.primitive_functions;

import wacc.backend.ProgramGenerator;
import wacc.backend.instructions.Branch;
import wacc.backend.instructions.Compare;
import wacc.backend.instructions.ConditionCode;
import wacc.backend.instructions.Instruction;
import wacc.backend.instructions.Load;
import wacc.backend.instructions.addr_modes.Address;
import wacc.backend.labels.code.PrimitiveLabel;
import wacc.backend.labels.data.DataLabel;
import wacc.backend.operands.ImmediateNum;
import wacc.backend.registers.Register;
import java.util.ArrayList;
import java.util.List;

public class PairElemNullAccessCheck {

  private static final DataLabel nullLabel = new DataLabel(
      "\"NullReferenceError: dereference a null reference\\n\\0\"");


  public static PrimitiveLabel pairElemCheckProgram(ProgramGenerator program) {
    List<Instruction> instructions = new ArrayList<>();

    // CMP r0, #0
    instructions.add(new Compare(Register.R0, ImmediateNum.ZERO));

    // add null label to data section of program
    program.addData(nullLabel);

    // LDREQ r0, =msg_0
    instructions
        .add(new Load(ConditionCode.EQ, Register.R0,
            new Address(nullLabel.getLabelName())));

    // include runtime error primitive function in code base
    PrimitiveLabel runtimeErrorPrimitive
        = RuntimeError.printRuntimeErrorCheck(program);
    program.addPrimitive(RuntimeError.printRuntimeErrorCheck(program));

    // BLEQ p_throw_runtime_error
    instructions.add(new Branch(ConditionCode.EQ,
        runtimeErrorPrimitive.getLabelName(), true));

    return
        new PrimitiveLabel("check_null_pointer",
            instructions, program).wrap();
  }
}
