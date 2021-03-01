package backend.primitive_functions;

import backend.ProgramGenerator;
import backend.instructions.Branch;
import backend.instructions.Compare;
import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.addr_modes.Address;
import backend.instructions.addr_modes.ZeroOffset;
import backend.labels.code.PrimitiveLabel;
import backend.labels.data.DataLabel;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import java.util.ArrayList;
import java.util.List;

public class PrintArrayBoundsChecks {

  private static final DataLabel negLabel = new DataLabel(
      "\"ArrayIndexOutOfBoundsError: negative index\\n\\0\"");


  private static final DataLabel largeLabel = new DataLabel(
      "\"ArrayIndexOutOfBoundsError: index too large\\n\\0\"");


  public static PrimitiveLabel printArrayIndexCheck(ProgramGenerator program) {
    List<Instruction> instructions = new ArrayList<>();

    // CMP r0, #0
    instructions.add(new Compare(Register.R0, ImmediateNum.ZERO));

    // add msg data to program
    program.addData(negLabel);

    // LDRLT r0, =msg_negIndex
    instructions.add(new Load(ConditionCode.LT, Register.R0,
        new Address(negLabel.getLabelName())));

    // include runtime error primitive function in code base
    PrimitiveLabel runtimeErrorPrimitive
        = RuntimeError.printRuntimeErrorCheck(program);
    program.addPrimitive(RuntimeError.printRuntimeErrorCheck(program));

    // BLLT p_throw_runtime_error
    instructions.add(new Branch(ConditionCode.LT,
        runtimeErrorPrimitive.getLabelName(), true));

    // LDR r1, [r1]
    instructions.add(new Load(Register.R1, new ZeroOffset(Register.R1)));

    // CMP r0, r1
    instructions.add(new Compare(Register.R0, Register.R1));

    // add msg data to program
    program.addData(largeLabel);

    // LDRCS ro, =msg_largeIndex
    instructions.add(new Load(ConditionCode.CS, Register.R0,
            new Address(largeLabel.getLabelName())));

    // BLCS p_throw_runtime_error
    instructions.add(new Branch(ConditionCode.CS,
        "p_throw_runtime_error", true));

    return new PrimitiveLabel("check_array_bounds", instructions, program).wrap();
  }

}
