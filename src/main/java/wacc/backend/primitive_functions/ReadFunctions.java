package wacc.backend.primitive_functions;

import wacc.backend.ProgramGenerator;
import wacc.backend.instructions.Branch;
import wacc.backend.instructions.Instruction;
import wacc.backend.instructions.Load;
import wacc.backend.instructions.Move;
import wacc.backend.instructions.addr_modes.Address;
import wacc.backend.instructions.arithmetic.Arithmetic;
import wacc.backend.instructions.arithmetic.ArithmeticOpcode;
import wacc.backend.labels.code.PrimitiveLabel;
import wacc.backend.labels.data.DataLabel;
import wacc.backend.operands.ImmediateNum;
import wacc.backend.registers.Register;
import java.util.ArrayList;
import java.util.List;

public class ReadFunctions {

  private static final DataLabel intFormat = new DataLabel("\"%d\\0\"");
  private static final DataLabel charFormat = new DataLabel("\" %c\\0\"");
  final static int WORD_SIZE = 4;
  
  public static PrimitiveLabel readIntFunction(ProgramGenerator program) {
    List<Instruction> ret = new ArrayList<>();

    // Mov R1, R0
    ret.add(new Move(Register.R1, Register.R0));

    // add data label to program
    program.addData(intFormat);

    // LDR R0, =msg_0
    ret.add(new Load(Register.R0, new Address(intFormat.getLabelName())));

    // Add R0, R0, #4
    ret.add(new Arithmetic(ArithmeticOpcode.ADD, Register.R0, Register.R0,
        new ImmediateNum(WORD_SIZE), false));

    // BL scanf
    ret.add(new Branch("scanf", true));

    return new PrimitiveLabel("read_int", ret, program).wrap();
  }

  public static PrimitiveLabel readCharFunction(ProgramGenerator program) {
    List<Instruction> ret = new ArrayList<>();

    // Mov R1, R0
    ret.add(new Move(Register.R1, Register.R0));

    // add data label to program
    program.addData(charFormat);

    // LDR R0, =msg_0
    ret.add(new Load(Register.R0, new Address(charFormat.getLabelName())));

    // Add R0, R0, #4
    ret.add(new Arithmetic(ArithmeticOpcode.ADD, Register.R0, Register.R0,
        new ImmediateNum(WORD_SIZE), false));

    // BL scanf
    ret.add(new Branch("scanf", true));

    return new PrimitiveLabel("read_char", ret, program).wrap();
  }
}
