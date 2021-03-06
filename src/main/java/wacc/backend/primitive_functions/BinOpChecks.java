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

public class BinOpChecks {

  private static final DataLabel overflowLabel
      = new DataLabel("\"OverflowError: the result is too small/large to "
      + "store in a 4-byte signed-integer.\\n\"");

  private static final DataLabel divByZeroLabel
      = new DataLabel("\"DivideByZeroError: divide or modulo by zero\\n\\0\"");

  public static PrimitiveLabel printOverflowCheck(ProgramGenerator program) {
    List<Instruction> ret = new ArrayList<>();

    // add overflow data label to program
    program.addData(overflowLabel);

    // LDR r0, overflowLabel
    ret.add(new Load(Register.R0,
        new Address(overflowLabel.getLabelName())));

    // include runtime error function in code base
    PrimitiveLabel runtimeErrorPrimitive
        = RuntimeError.printRuntimeErrorCheck(program);
    program.addPrimitive(runtimeErrorPrimitive);

    // BL p_throw_runtime_error
    ret.add(new Branch(runtimeErrorPrimitive.getLabelName(), true));

    return new PrimitiveLabel("throw_overflow_error", ret, program);
  }

  public static PrimitiveLabel printDivZeroCheck(ProgramGenerator program) {
    List<Instruction> ret = new ArrayList<>();

    // CMP r1, #0
    ret.add(new Compare(Register.R1, ImmediateNum.ZERO));

    // add div by zero msg data label to program
    program.addData(divByZeroLabel);

    // LDREQ r0, =msg_0
    ret.add(new Load(ConditionCode.EQ, Register.R0,
        new Address(divByZeroLabel.getLabelName())));

    // include runtime error function in code base
    PrimitiveLabel runtimeErrorPrimitive
        = RuntimeError.printRuntimeErrorCheck(program);
    program.addPrimitive(runtimeErrorPrimitive);

    // BLEQ p_throw_runtime_error
    ret.add(new Branch(ConditionCode.EQ,
        runtimeErrorPrimitive.getLabelName(), true));

    return new PrimitiveLabel("check_divide_by_zero", ret, program).wrap();
  }

}
