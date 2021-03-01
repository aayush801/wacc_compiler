package backend.primitive_functions;

import backend.ProgramGenerator;
import backend.instructions.Branch;
import backend.instructions.Compare;
import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.addr_modes.Address;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.instructions.addr_modes.ZeroOffset;
import backend.instructions.stack_instructions.Pop;
import backend.instructions.stack_instructions.Push;
import backend.labels.code.PrimitiveLabel;
import backend.labels.data.DataLabel;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import java.util.ArrayList;
import java.util.List;

public class FreeFunction {

  private static final DataLabel nullLabel = new DataLabel(
      "\"NullReferenceError: dereference a null reference\\n\\0\"");


  public static PrimitiveLabel printPairFree(ProgramGenerator program) {
    List<Instruction> ret = new ArrayList<>();

    // CMP r0, #0 - null check
    ret.add(new Compare(Register.R0, ImmediateNum.ZERO));

    // add null message to data section of program
    program.addData(nullLabel);

    // LDREQ r0, =msg_0
    ret.add(
        new Load(ConditionCode.EQ, Register.R0,
            new Address(nullLabel.getLabelName())));

    // include runtime error primitive function in code base
    PrimitiveLabel runtimeErrorPrimitive
        = RuntimeError.printRuntimeErrorCheck(program);
    program.addPrimitive(RuntimeError.printRuntimeErrorCheck(program));

    // BEQ p_throw_runtime_error
    ret.add(new Branch(ConditionCode.EQ, runtimeErrorPrimitive.getLabelName(),
        false));

    // PUSH {r0}
    ret.add(new Push(Register.R0));

    // LDR r0, [r0]
    ret.add(new Load(Register.R0,
        new ZeroOffset(Register.R0)));

    // BL free
    ret.add(new Branch("free", true));

    // LDR ro, [sp]
    ret.add(new Load(Register.R0, new ZeroOffset(program.SP)));

    // LDR ro, [r0, #4]
    ret.add(new Load(Register.R0,
        new ImmediateOffset(Register.R0, new ImmediateNum(4))));

    // BL free
    ret.add(new Branch("free", true));

    // POP {r0}
    ret.add(new Pop(Register.R0));

    // BL free
    ret.add(new Branch("free", true));

    return new PrimitiveLabel("free_pair", ret, program).wrap();
  }
}
