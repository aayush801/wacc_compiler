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

  private static final DataLabel stringFormat = new DataLabel("\"%.*s\\0\"");
  private static final DataLabel intFormat = new DataLabel("\"%d\\0\"");
  private static final DataLabel newLineFormat = new DataLabel("\"\\0\"");
  private static final DataLabel referenceFormat = new DataLabel("\"%p\\0\"");
  private static final DataLabel trueLabel = new DataLabel("\"true\\0\"");
  private static final DataLabel falseLabel = new DataLabel("\"false\\0\"");



  public static PrimitiveLabel printReference(ProgramGenerator program) {

    List<Instruction> instructions = new ArrayList<>();

    //		MOV r1, r0
    instructions.add(new Move(R1, R0));

    // add referenceFormat label to program data section
    program.addData(referenceFormat);

    //		LDR r0, =msg_0
    instructions.add(new Load(R0, new Address(referenceFormat.getLabelName())));

    //		ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, R0, R0, new ImmediateNum(4), false));

    //		print and flush the console
    printAndFlushOutput(instructions, "printf");

    return new PrimitiveLabel("print_reference", instructions, program);

  }


  public static PrimitiveLabel printString(ProgramGenerator program) {

    List<Instruction> instructions = new ArrayList<>();

    //    LDR r1, [r0]
    instructions.add(new Load(R1, new ZeroOffset(R0)));

    // 		ADD r2, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, R2, R0,
        new ImmediateNum(4), false));

    // add stringFormat data label to code
    program.addData(stringFormat);

    //		LDR r0, =msg_1
    instructions.add(new Load(R0, new Address(stringFormat.getLabelName())));

    //		ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, R0, R0,
        new ImmediateNum(4), false));

    //		print and flush the console
    printAndFlushOutput(instructions, "printf");

    return new PrimitiveLabel("print_string", instructions, program);

  }

  public static PrimitiveLabel printBool(ProgramGenerator program) {
    List<Instruction> instructions = new ArrayList<>();
    //		CMP r0, #0
    instructions.add(new Compare(R0, new ImmediateNum(0)));

    // add data labels for true and false to program
    program.addData(trueLabel);
    program.addData(falseLabel);

    //		LDRNE r0, =msg_3
    instructions
        .add(new Load(ConditionCode.NE, R0, new Address(trueLabel.getLabelName()),
            false, false));

    //		LDREQ r0, =msg_4
    instructions
        .add(new Load(ConditionCode.EQ, R0,
            new Address(falseLabel.getLabelName()), false, false));

    //		ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, R0, R0,
        new ImmediateNum(4), false));

    //		print and flush the console
    printAndFlushOutput(instructions, "printf");

    return new PrimitiveLabel("print_bool", instructions, program);
  }

  public static PrimitiveLabel printInt(ProgramGenerator program) {
    List<Instruction> instructions = new ArrayList<>();

    //  MOV r1, r0
    instructions.add(new Move(R1, R0));

    // add int format data label to program
    program.addData(intFormat);

    //	LDR r0, =msg_0
    instructions.add(new Load(R0, new Address(intFormat.getLabelName())));

    //	ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, R0, R0,
        new ImmediateNum(4), false));

    //		print and flush the console
    printAndFlushOutput(instructions, "printf");

    return new PrimitiveLabel("print_int", instructions, program);
  }

  public static PrimitiveLabel printLine(ProgramGenerator program) {

    List<Instruction> instructions = new ArrayList<>();

    // add newline data label to program
    program.addData(newLineFormat);

    //		LDR r0, =msg_5
    instructions
        .add(new Load(R0, new Address(newLineFormat.getLabelName())));

    //		ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, R0, R0,
        new ImmediateNum(4), false));

    //		print and flush the console
    printAndFlushOutput(instructions, "puts");

    return new PrimitiveLabel("print_ln", instructions, program);
  }

  /* ======================== HELPER METHODS ======================== */

  private static void printAndFlushOutput(List<Instruction> instructions, String printf) {
    //		BL printf
    instructions.add(new Branch(printf, true));

    //		MOV r0, #0
    instructions.add(new Move(R0, new ImmediateNum(0)));

    //		print and flush the console
    instructions.add(new Branch("fflush", true));
  }

}
