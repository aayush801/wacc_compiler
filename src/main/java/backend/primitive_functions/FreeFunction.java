package backend.primitive_functions;

import backend.ProgramGenerator;
import backend.instructions.*;
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

    private static boolean printedNull = false;
    private static DataLabel nullLabel;

    public static void printNullDereferenceMessage(ProgramGenerator program) {
        if (!printedNull) {
            nullLabel = new DataLabel("\"NullReferenceError: dereference a null reference\\n\\0\"");
            program.addData(nullLabel);
            printedNull = true;
        }
    }

    public static PrimitiveLabel printPairFree(ProgramGenerator program) {
        List<Instruction> ret = new ArrayList<>();

        // CMP r0, #0 - null check
        ret.add(new Compare(new Register(0), new ImmediateNum(0)));

        // LDREQ r0, =msg_0
        ret.add(new Load(ConditionCode.EQ, new Register(0), new Address(nullLabel.getLabelName()), false, false));

        // BEQ p_throw_runtime_error
        ret.add(new Branch(ConditionCode.EQ, "p_throw_runtime_error", false));

        // PUSH {r0}
        ret.add(new Push(new Register(0)));

        // LDR r0, [r0]
        ret.add(new Load(new Register(0), new ZeroOffset(new Register(0))));

        // BL free
        ret.add(new Branch("free", true));

        // LDR ro, [sp]
        ret.add(new Load(new Register(0), new ZeroOffset(program.SP)));

        // LDR ro, [r0, #4]
        ret.add(new Load(new Register(0), new ImmediateOffset(new Register(0), new ImmediateNum(4))));

        // BL free
        ret.add(new Branch("free", true));

        // POP {r0}
        ret.add(new Pop(new Register(0)));

        // BL free
        ret.add(new Branch("free", true));

        return new PrimitiveLabel("free_pair", ret, program);
    }
}