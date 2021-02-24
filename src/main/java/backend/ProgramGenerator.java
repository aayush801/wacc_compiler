package backend;

import backend.instructions.Instruction;
import backend.instructions.stack_instructions.Pop;
import backend.instructions.stack_instructions.Push;
import backend.labels.code.InstructionLabel;
import backend.labels.data.DataLabel;
import backend.labels.text.TextLabel;
import backend.registers.LinkRegister;
import backend.registers.ProgramCounter;
import backend.registers.Register;
import backend.registers.StackPointer;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import middleware.symbol_table.SymbolTable;

public class ProgramGenerator {

  private final Set<DataLabel> dataSection = new LinkedHashSet<>();
  private final Set<TextLabel> textSection = new LinkedHashSet<>();
  private final Set<InstructionLabel> codeSection = new LinkedHashSet<>();
  private int labelsUsed = 0;
  public final List<Register> registers = new ArrayList<>();
  public final Register PC = new ProgramCounter();
  public final LinkRegister LR = new LinkRegister();
  public final StackPointer SP = new StackPointer();

  public ProgramGenerator() {
    // only use registers from 4 onwards
    for (int i = 4; i <= 12; i++) {

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

  // allocate space on the stack for local variables
  public List<Instruction> allocateStackSpace(SymbolTable ST) {
    List<Instruction> stackInstructions = new ArrayList<>();
    int estimatedStackSize = ST.calculateScopeSize();

    // no variables declared in this scope, so just return empty list.
    if (estimatedStackSize == 0) {
      return stackInstructions;
    }

    // decrement virtual stack and generate stack instruction
    stackInstructions.add(SP.decrement(estimatedStackSize));

    return stackInstructions;
  }

  public List<Instruction> deallocateStackSpace(SymbolTable ST) {
    List<Instruction> stackInstructions = new ArrayList<>();
    int estimatedStackSize = ST.calculateScopeSize();

    // no variables declared in this scope, so just return empty list.
    if (estimatedStackSize == 0) {
      return stackInstructions;
    }

    // decrement virtual stack and generate stack instruction
    stackInstructions.add(SP.increment(estimatedStackSize));

    return stackInstructions;
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

    printSection(codeSection, builder);
    //codeSection.forEach(builder::append);

    return builder.toString();
  }


  private void printSection(Set<InstructionLabel> section, StringBuilder builder) {
    ArrayList<InstructionLabel> setAsList = new ArrayList<>(section);
    for (int i = setAsList.size() - 1; i >= 0; i--) {
      builder.append(setAsList.get(i));
    }
  }

  public Set<InstructionLabel> getCodeSection() {
    return codeSection;
  }

  public int nextLabelNumber() {
    return labelsUsed++;
  }
}
