package backend;

import backend.labels.DataLabel;
import backend.labels.InstructionLabel;
import backend.labels.TextLabel;
import backend.registers.LinkRegister;
import backend.registers.ProgramCounter;
import backend.registers.Register;
import backend.registers.StackPointer;
import java.util.ArrayList;
import java.util.List;

public class ProgramGenerator {

  private final List<DataLabel> dataSection = new ArrayList<>();
  private final List<TextLabel> textSection = new ArrayList<>();
  private final List<InstructionLabel> mainSection = new ArrayList<>();

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

  public void addMain(InstructionLabel label) {
    mainSection.add(label);
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
    mainSection.forEach(builder::append);

    return builder.toString();
  }
}
