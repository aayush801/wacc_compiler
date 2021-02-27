package backend.primitive_functions;

import backend.ProgramGenerator;
import backend.instructions.Branch;
import backend.instructions.Compare;
import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.Move;
import backend.instructions.addr_modes.Address;
import backend.instructions.addr_modes.ZeroOffset;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.labels.code.PrimitiveLabel;
import backend.labels.data.DataLabel;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import java.util.ArrayList;
import java.util.List;

public class PrintFunctions {

  private final static Register R0 = new Register(0);
  private final static Register R1 = new Register(1);
  private final static Register R2 = new Register(2);
  private final static Register R3 = new Register(3);

  public static PrimitiveLabel printString(ProgramGenerator program) {

    List<Instruction> instructions = new ArrayList<>();

    //    LDR r1, [r0]
    instructions.add(new Load(R1, new ZeroOffset(R0)));

    // 		ADD r2, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, R2, R0,
        new ImmediateNum(4), false));

    //msg_1:
    //		.word 5
    //		.ascii	"%.*s\0"
    DataLabel printFormat = new DataLabel("\"%.*s\\0\"");

    program.addData(printFormat);

    //		LDR r0, =msg_1
    instructions.add(new Load(R0, new Address(printFormat.getLabelName())));

    //		ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, R0, R0,
        new ImmediateNum(4), false));

    //		BL printf
    instructions.add(new Branch("printf", true));

    //		MOV r0, #0
    instructions.add(new Move(R0, new ImmediateNum(0)));

    //		BL fflush
    instructions.add(new Branch("fflush", true));

    return new PrimitiveLabel("print_string", instructions, program);

  }

  public static PrimitiveLabel printBool(ProgramGenerator program) {
    List<Instruction> instructions = new ArrayList<>();
    //		CMP r0, #0
    instructions.add(new Compare(R0, new ImmediateNum(0)));

    //    msg_3:
    //    		.word 5
    //    		.ascii	"true\0"
    //    msg_4:
    //    		.word 6
    //    		.ascii	"false\0"
    DataLabel trueLabel = new DataLabel("\"true\0\"");
    DataLabel falseLabel = new DataLabel("\"false\0\"");

    program.addData(trueLabel);
    program.addData(falseLabel);

    //		LDRNE r0, =msg_3
    instructions
        .add(new Load(ConditionCode.NE, R0, new Address(trueLabel.getLabelName()),
            false, false));

    //		LDREQ r0, =msg_4
    instructions
        .add(new Load(ConditionCode.NE, R0,
            new Address(falseLabel.getLabelName()), false, false));

    //		ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, R0, R0,
        new ImmediateNum(4), false));

    //		BL printf
    instructions.add(new Branch("printf", true));

    //		MOV r0, #0
    instructions.add(new Move(R0, new ImmediateNum(0)));

    //		BL fflush
    instructions.add(new Branch("fflush", true));

    return new PrimitiveLabel("print_bool", instructions, program);
  }

  public static PrimitiveLabel printInt(ProgramGenerator program) {
    List<Instruction> instructions = new ArrayList<>();

    //  MOV r1, r0
    instructions.add(new Move(R1, R0));

    //  msg_0:
    //		.word 3
    //		.ascii	"%d\0"
    DataLabel format = new DataLabel("\"%d\\0\"");

    program.addData(format);

    //	LDR r0, =msg_0
    instructions.add(new Load(R0, new Address(format.getLabelName())));

    //	ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, R0, R0,
        new ImmediateNum(4), false));

    //	BL printf
    instructions.add(new Branch("printf", true));

    //	MOV r0, #0
    instructions.add(new Move(R0, new ImmediateNum(0)));

    //	BL fflush
    instructions.add(new Branch("fflush", true));

    return new PrimitiveLabel("print_int", instructions, program);
  }

  public static PrimitiveLabel printLine(ProgramGenerator program) {

    List<Instruction> instructions = new ArrayList<>();
    //msg_5:
    //		.word 1
    //		.ascii	"\0"
    DataLabel nullTerminator = new DataLabel("\"\\0\"");
    program.addData(nullTerminator);

    //		LDR r0, =msg_5
    instructions
        .add(new Load(R0, new Address(nullTerminator.getLabelName())));

    //		ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, R0, R0,
        new ImmediateNum(4), false));

    //		BL puts
    instructions.add(new Branch("puts", true));

    //		MOV r0, #0
    instructions.add(new Move(R0, new ImmediateNum(0)));

    //		BL fflush
    instructions.add(new Branch("fflush", true));

    return new PrimitiveLabel("print_ln", instructions, program);
  }

}