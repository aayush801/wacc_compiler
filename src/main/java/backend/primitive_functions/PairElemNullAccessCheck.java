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

public class PairElemNullAccessCheck {

    private static final DataLabel nullLabel = new DataLabel("\"NullReferenceError: dereference a null reference\\n\\0\"");;


    public static PrimitiveLabel pairElemCheckProgram(ProgramGenerator program) {
        List<Instruction> instructions = new ArrayList<>();

        // CMP r0, #0
        instructions.add(new Compare(new Register(0), new ImmediateNum(0)));

        // add null label to data section of program
        program.addData(nullLabel);

        // LDREQ r0, =msg_0
        instructions.add(new Load(ConditionCode.EQ, new Register(0), new Address(nullLabel.getLabelName()), false, false));

        // BLEQ p_throw_runtime_error
        instructions.add(new Branch(ConditionCode.EQ, "p_throw_runtime_error", true));

        return new PrimitiveLabel("check_null_pointer", instructions, program);
    }

}
