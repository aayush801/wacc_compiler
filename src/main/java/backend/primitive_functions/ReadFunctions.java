package backend.primitive_functions;

import backend.ProgramGenerator;
import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.Move;
import backend.instructions.addr_modes.Address;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.labels.code.PrimitiveLabel;
import backend.labels.data.DataLabel;
import backend.operands.ImmediateNum;
import backend.registers.Register;

import java.util.ArrayList;
import java.util.List;

public class ReadFunctions {

    private static final DataLabel intFormat = new DataLabel("\"%d\\0\"");
    private static final DataLabel charFormat = new DataLabel("\" %c\\0\"");

    public static PrimitiveLabel readIntFunction(ProgramGenerator program) {
        List<Instruction> ret = new ArrayList<>();

        // Mov R1, R0
        ret.add(new Move(new Register(1), new Register(0)));

        // add data label to program
        program.addData(intFormat);

        // LDR R0, =msg_0
        ret.add(new Load(new Register(0), new Address(intFormat.getLabelName())));

        // Add R0, R0, #4
        ret.add(new Arithmetic(ArithmeticOpcode.ADD, new Register(0), new Register(0), new ImmediateNum(4), false));

        // BL scanf
        ret.add(new Branch("scanf", true));

        return new PrimitiveLabel("read_int", ret, program).wrap();
    }

    public static PrimitiveLabel readCharFunction(ProgramGenerator program) {
        List<Instruction> ret = new ArrayList<>();

        // Mov R1, R0
        ret.add(new Move(new Register(1), new Register(0)));

        // add data label to program
        program.addData(charFormat);

        // LDR R0, =msg_0
        ret.add(new Load(new Register(0), new Address(charFormat.getLabelName())));

        // Add R0, R0, #4
        ret.add(new Arithmetic(ArithmeticOpcode.ADD, new Register(0), new Register(0), new ImmediateNum(4), false));

        // BL scanf
        ret.add(new Branch("scanf", true));

        return new PrimitiveLabel("read_char", ret, program).wrap();
    }
}
