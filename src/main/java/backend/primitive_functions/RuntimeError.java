package backend.primitive_functions;

import backend.ProgramGenerator;
import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Move;
import backend.labels.Label;
import backend.labels.code.CodeLabel;
import backend.labels.code.PrimitiveLabel;
import backend.operands.ImmediateNum;
import backend.registers.Register;

import java.util.ArrayList;
import java.util.List;

public class RuntimeError {

    public static CodeLabel printRuntimeErrorCheck(ProgramGenerator program) {
        List<Instruction> ret = new ArrayList<>();

        // BL p_print_string
        ret.add(new Branch("p_print_string", true));

        // add p_print_string if necessary.
        System.out.println("hi");
        program.addCode(PrintFunctions.printString(program));
        System.out.println("he");

        // MOV r0, #-1
        ret.add(new Move(new Register(0), new ImmediateNum(-1)));

        // BL exit
        ret.add(new Branch("exit", true));

        return new CodeLabel("p_throw_runtime_error", ret);
    }

}
