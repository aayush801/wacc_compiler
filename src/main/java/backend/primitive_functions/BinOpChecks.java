package backend.primitive_functions;

import backend.ProgramGenerator;
import backend.instructions.*;
import backend.instructions.addr_modes.Address;
import backend.labels.code.PrimitiveLabel;
import backend.labels.data.DataLabel;
import backend.operands.ImmediateNum;
import backend.registers.Register;

import java.util.ArrayList;
import java.util.List;

public class BinOpChecks {

    private static final DataLabel overflowLabel = new DataLabel("\"OverflowError: the result is too small/large to store in a 4-byte signed-integer.\\n\"");

    private static final DataLabel divByZeroLabel = new DataLabel("\"DivideByZeroError: divide or modulo by zero\\n\\0\"");

    public static PrimitiveLabel printOverflowCheck(ProgramGenerator program) {
        List<Instruction> ret = new ArrayList<>();

        // LDR r0, overflowLabel
        ret.add(new Load(new Register(0), new Address(overflowLabel.getLabelName())));

        // BL p_throw_runtime_error
        ret.add(new Branch("p_throw_overflow_error", true));

        return new PrimitiveLabel("throw_overflow_error", ret, program);
    }

    public static PrimitiveLabel printDivZeroCheck(ProgramGenerator program) {
        List<Instruction> ret = new ArrayList<>();

        // CMP r1, #0
        ret.add(new Compare(new Register(1), new ImmediateNum(0)));

        // LDREQ r0, =msg_0
        ret.add(new Load(ConditionCode.EQ, new Register(0), new Address(divByZeroLabel.getLabelName())));

        // BLEQ p_throw_runtime_error
        ret.add(new Branch(ConditionCode.EQ, "p_throw_runtime_error", true));

        return new PrimitiveLabel("check_divide_by_zero", ret, program);
    }

}
