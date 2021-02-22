package backend.functions;

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
import backend.instructions.stack_instructions.Pop;
import backend.instructions.stack_instructions.Push;
import backend.labels.code.PrimitiveLabel;
import backend.labels.data.DataLabel;
import backend.operands.Immediate;
import java.util.ArrayList;
import java.util.List;

public class PrintFunctions {

  public static PrimitiveLabel printString(ProgramGenerator program) {

    List<Instruction> instructions = new ArrayList<>();

    //    LDR r1, [r0]
    instructions.add(new Load(program.registers.get(1), new ZeroOffset(program.registers.get(0))));

    // 		ADD r2, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, program.registers.get(2), program.registers.get(0), new Immediate(4)));

    //msg_1:
    //		.word 5
    //		.ascii	"%.*s\0"
    DataLabel printFormat = new DataLabel("\"%.*s\0\"");
    program.addData(printFormat);

    //		LDR r0, =msg_1
    instructions.add(new Load(program.registers.get(0), new Address(printFormat.getLabelName())));

    //		ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, program.registers.get(0), program.registers.get(0), new Immediate(4)));

    //		BL printf
    instructions.add(new Branch("printf", true));

    //		MOV r0, #0
    instructions.add(new Move(program.registers.get(0), new Immediate(0)));

    //		BL fflush
    instructions.add(new Branch("fflush", true));

    return new PrimitiveLabel("print_string", program.encapsulateFunction(instructions));

  }

  public static PrimitiveLabel printBool(ProgramGenerator program) {
    List<Instruction> instructions = new ArrayList<>();
    //		CMP r0, #0
    instructions.add(new Compare(program.registers.get(0), new Immediate(0)));

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
    instructions.add(new Load(program.registers.get(0), new Address(trueLabel.getLabelName()), ConditionCode.NE));

    //		LDREQ r0, =msg_4
    instructions.add(new Load(program.registers.get(0), new Address(falseLabel.getLabelName()), ConditionCode.EQ));

    //		ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, program.registers.get(0), program.registers.get(0), new Immediate(4)));

    //		BL printf
    instructions.add(new Branch("printf", true));

    //		MOV r0, #0
    instructions.add(new Move(program.registers.get(0), new Immediate(0)));

    //		BL fflush
    instructions.add(new Branch("fflush", true));


    return new PrimitiveLabel("print_bool", program.encapsulateFunction(instructions));
  }

  public static PrimitiveLabel printLine(ProgramGenerator program) {

    List<Instruction> instructions = new ArrayList<>();
    //msg_5:
    //		.word 1
    //		.ascii	"\0"
    DataLabel nullTerminator = new DataLabel("\"\0\"");
    program.addData(nullTerminator);

    //		LDR r0, =msg_5
    instructions.add(new Load(program.registers.get(0), new Address(nullTerminator.getLabelName())));

    //		ADD r0, r0, #4
    instructions.add(new Arithmetic(ArithmeticOpcode.ADD, program.registers.get(0), program.registers.get(0), new Immediate(4)));

    //		BL puts
    instructions.add(new Branch("puts", true));

    //		MOV r0, #0
    instructions.add(new Move(program.registers.get(0), new Immediate(0)));

    //		BL fflush
    instructions.add(new Branch("fflush", true));

    return new PrimitiveLabel("print_ln", program.encapsulateFunction(instructions));
  }

}
