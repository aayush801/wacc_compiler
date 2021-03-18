package wacc.backend.primitive_functions;

import wacc.backend.ProgramGenerator;
import wacc.backend.instructions.Branch;
import wacc.backend.instructions.Compare;
import wacc.backend.instructions.ConditionCode;
import wacc.backend.instructions.Instruction;
import wacc.backend.instructions.Load;
import wacc.backend.instructions.addr_modes.Address;
import wacc.backend.instructions.addr_modes.ImmediateOffset;
import wacc.backend.instructions.addr_modes.ZeroOffset;
import wacc.backend.instructions.stack_instructions.Pop;
import wacc.backend.instructions.stack_instructions.Push;
import wacc.backend.labels.code.PrimitiveLabel;
import wacc.backend.labels.data.DataLabel;
import wacc.backend.operands.ImmediateNum;
import wacc.backend.registers.Register;
import java.util.ArrayList;
import java.util.List;

public class FreeFunction {

  private static final DataLabel nullLabel = new DataLabel(
      "\"NullReferenceError: dereference a null reference\\n\\0\"");
  public static PrimitiveLabel freeReference(ProgramGenerator program) {
    List<Instruction> ret = new ArrayList<>();

    // CMP r0, #0 - null check
    ret.add(new Compare(Register.R0, ImmediateNum.ZERO));

    // add null message to data section of program
    program.addData(nullLabel);

    // LDREQ r0, =null
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

    // BL free
    ret.add(new Branch("free", true));

    return new PrimitiveLabel("free_reference", ret, program).wrap();
  }


  public static PrimitiveLabel freePair(ProgramGenerator program) {
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
