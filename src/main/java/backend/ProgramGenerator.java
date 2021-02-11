package backend;

import backend.instructions.Instruction;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.instructions.stack_instructions.Pop;
import backend.instructions.stack_instructions.Push;
import backend.labels.data.DataLabel;
import backend.labels.code.InstructionLabel;
import backend.labels.text.TextLabel;
import backend.operands.ImmediateNum;
import backend.registers.LinkRegister;
import backend.registers.ProgramCounter;
import backend.registers.Register;
import backend.registers.StackPointer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import middleware.symbol_table.SymbolTable;

public class ProgramGenerator {

  private final Set<DataLabel> dataSection = new HashSet<>();
  private final Set<TextLabel> textSection = new HashSet<>();
  private final Set<InstructionLabel> codeSection = new HashSet<>();

  public final List<Register> registers = new ArrayList<>();
  public final Register PC = new ProgramCounter();
  public final LinkRegister LR = new LinkRegister();
  public final StackPointer SP = new StackPointer();

  public ProgramGenerator() {

    for (int i = 0; i <= 12; i++) {

      registers.add(new Register(i));

    }

  }

  public void addData(DataLabel label) {

    dataSection.add(label);

  }

  public void addText(TextLabel label) {

    textSection.add(label);

  }

  public void addCode(InstructionLabel label) {

    codeSection.add(label);

  }

  public List<Instruction> encapsulateFunction(List<Instruction> instructions){

    //    PUSH {lr}
    instructions.add(0, new Push(LR));

    // instruction body unchanged

    //		POP {pc}
    instructions.add(new Pop(PC));

    return instructions;
  }

  public List<Instruction> encapsulateScope(SymbolTable scopeST, List<Instruction> instructions) {
    int sizeOfVariablesDeclaredInScope = scopeST.getAllocatedInThisScope();

    // no variables declared in this scope, so just return.
    if (sizeOfVariablesDeclaredInScope == 0) {
      return instructions;
    }
    Instruction decrementStack =
        new Arithmetic(ArithmeticOpcode.SUB, SP, SP,
            new ImmediateNum(sizeOfVariablesDeclaredInScope), false);

    instructions.add(0, decrementStack);

    Instruction incrementStack =
        new Arithmetic(ArithmeticOpcode.ADD, SP, SP,
            new ImmediateNum(sizeOfVariablesDeclaredInScope), false);

    instructions.add(incrementStack);
    return instructions;
  }


  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    // add .data section (if there is one)
    if (!dataSection.isEmpty()) {
      builder.append(".data \n\n");
      dataSection.forEach(builder::append);
    }

    builder.append("\n");

    // add .text section
    builder.append(".text \n\n");
    textSection.forEach(builder::append);

    builder.append("\n");

    builder.append(".global main \n");
    codeSection.forEach(builder::append);

    return builder.toString();
  }
}
