package backend.primitive_functions;

import backend.ProgramGenerator;
import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Move;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.labels.code.PrimitiveLabel;
import backend.operands.ImmediateNum;
import backend.registers.Register;

import java.util.ArrayList;
import java.util.List;

public class ReadFunctions {

    public static PrimitiveLabel readIntFunction(ProgramGenerator program) {
        List<Instruction> ret = new ArrayList<>();

        // Mov R1, R0
        ret.add(new Move(new Register(1), new Register(0)));

        // LDR R0, =msg_charFormat

        // Add R0, R0, #4
        ret.add(new Arithmetic(ArithmeticOpcode.ADD, new Register(0), new Register(0), new ImmediateNum(4), false));

        // BL scanf
        ret.add(new Branch("scanf", true));

        return new PrimitiveLabel("read_int", ret, program);
    }

    public static PrimitiveLabel readCharFunction(ProgramGenerator program) {
        List<Instruction> ret = new ArrayList<>();

        // Mov R1, R0
        ret.add(new Move(new Register(1), new Register(0)));

        // LDR R0, =msg_charFormat

        // Add R0, R0, #4
        ret.add(new Arithmetic(ArithmeticOpcode.ADD, new Register(0), new Register(0), new ImmediateNum(4), false));

        // BL scanf
        ret.add(new Branch("scanf", true));

        return new PrimitiveLabel("read_char", ret, program);
    }
}
