package backend;

import backend.instructions.Instruction;
import backend.instructions.stack_instructions.Pop;
import backend.instructions.stack_instructions.Push;
import backend.labels.code.CodeLabel;
import backend.labels.code.PrimitiveLabel;
import backend.labels.data.DataLabel;
import backend.labels.text.TextLabel;
import backend.registers.LinkRegister;
import backend.registers.ProgramCounter;
import backend.registers.Register;
import backend.registers.StackPointer;
import frontend.identifier_objects.VARIABLE;
import frontend.identifier_objects.basic_types.INT;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import middleware.symbol_table.SymbolTable;

public class ProgramGenerator {

  public final List<Register> registers = new ArrayList<>();
  public final Register PC = new ProgramCounter();
  public final LinkRegister LR = new LinkRegister();
  public final StackPointer SP = new StackPointer();
  private final Set<DataLabel> dataSection = new LinkedHashSet<>();
  private final Set<TextLabel> textSection = new LinkedHashSet<>();
  private final Set<CodeLabel> codeSection = new LinkedHashSet<>();

  // stores a set dependency functions which are hard coded
  private final Set<PrimitiveLabel> primitives = new LinkedHashSet<>();

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

  public void addCode(CodeLabel label) {

    codeSection.add(label);

  }

  public void addPrimitive(PrimitiveLabel label) {

    primitives.add(label);

  }

  // allocate space on the stack for local variables
  public List<Instruction> allocateStackSpace(SymbolTable ST) {
    List<Instruction> stackInstructions = new ArrayList<>();

    int estimatedStackSize = ST.calculateScopeSize();

    // no variables declared in this scope, so just return empty list.
    if (estimatedStackSize == 0) {
      return stackInstructions;
    }

    // save the stack state in the symbol table
    ST.saveStackState(SP);

    // decrement virtual stack and generate stack instruction
    stackInstructions.addAll(SP.decrement(estimatedStackSize));

    List<VARIABLE> variables = ST.getVariables();

    // push variables to stack (implicitly assigns them addresses)
    for (VARIABLE var : variables) {
      SP.push(var);
    }

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
    stackInstructions.addAll(SP.increment(estimatedStackSize));

    // restore the stack state in the symbol table
    ST.restoreStackState(SP);

    return stackInstructions;
  }


  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    // TODO: Remove the hardcoding.
//    this.addPrimitive(PrintFunctions.printString(this));

    // add .data section (if there is one)
    if (!dataSection.isEmpty()) {
      builder.append(".data\n\n");
      dataSection.forEach(builder::append);
    }

    builder.append("\n");

    // add .text section
    builder.append(".text\n\n");
    textSection.forEach(builder::append);

    builder.append("\n");

    builder.append(".global main\n");

    codeSection.forEach(builder::append);

    // imported functions are added after code section
    primitives.forEach(builder::append);

    // TODO: Remove the hardcoding.
//    builder.append(RuntimeError.printRuntimeErrorCheck(this));

    return builder.toString();
  }

  public void pushLR(List<Instruction> instructions) {
    //    PUSH {lr}
    instructions.add(0, new Push(LR));
    // decrements stack pointer and free pointer by 4 bytes
    SP.push(new VARIABLE(new INT()));
  }


  public void popPC(List<Instruction> instructions) {
    //		POP {pc}
    instructions.add(new Pop(PC));
    // increments stack pointer and free pointer by 4 bytes
    SP.pop(new VARIABLE(new INT()));
  }
}
