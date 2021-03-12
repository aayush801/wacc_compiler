package wacc.backend;

import wacc.backend.instructions.Instruction;
import wacc.backend.instructions.stack_instructions.Pop;
import wacc.backend.instructions.stack_instructions.Push;
import wacc.backend.labels.code.CodeLabel;
import wacc.backend.labels.code.PrimitiveLabel;
import wacc.backend.labels.data.DataLabel;
import wacc.backend.registers.LinkRegister;
import wacc.backend.registers.ProgramCounter;
import wacc.backend.registers.Register;
import wacc.backend.registers.StackPointer;
import wacc.frontend.identifier_objects.CLASS;
import wacc.frontend.identifier_objects.VARIABLE;
import wacc.frontend.identifier_objects.basic_types.INT;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import wacc.middleware.SymbolTable;

public class ProgramGenerator {

  public final List<Register> registers = new ArrayList<>();
  public final Register PC = new ProgramCounter();
  public final LinkRegister LR = new LinkRegister();
  public final StackPointer SP = new StackPointer();
  private final Set<DataLabel> dataSection = new LinkedHashSet<>();
  private final Set<CodeLabel> codeSection = new LinkedHashSet<>();
  private final List<LabelPair> loopLabels = new ArrayList<>();
  // stores a set dependency functions which are hard coded
  private final Set<PrimitiveLabel> primitives = new LinkedHashSet<>();
  private CLASS classScope = null;

  public ProgramGenerator() {

    // only use registers from 4 onwards
    for (int i = 4; i <= 12; i++) {

      registers.add(new Register(i));

    }

  }

  public void addData(DataLabel label) {

    dataSection.add(label);

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

    // add .data section (if there is one)
    if (!dataSection.isEmpty()) {
      builder.append(".data\n\n");
      dataSection.forEach(builder::append);
    }

    builder.append("\n");

    // add .text section (label)
    builder.append(".text\n");

    builder.append("\n");

    builder.append(".global main\n");

    codeSection.forEach(builder::append);

    // imported functions are added after code section
    primitives.forEach(builder::append);

    return builder.toString();
  }

  public void pushLR(List<Instruction> instructions) {

    //    PUSH {lr}
    instructions.add(0, new Push(LR));

    // decrements stack pointer and free pointer by 4bytes
    SP.push(new VARIABLE(new INT()));

  }


  public void popPC(List<Instruction> instructions) {

    //		POP {pc}
    instructions.add(new Pop(PC));

    // increments stack pointer and free pointer by 4 bytes
    SP.pop(new VARIABLE(new INT()));

  }

  public void addLoopLabels(String start, String end) {
    loopLabels.add(new LabelPair(start, end));
  }

  public String getLoopStartLabel() {
    return loopLabels.get(loopLabels.size() - 1).getFst();
  }

  public String getLoopEndLabel() {
    return loopLabels.get(loopLabels.size() - 1).getSnd();
  }

  public void popLoopLabels() {
    loopLabels.remove(loopLabels.size() - 1);
  }

  static class LabelPair {

    private final String fst, snd;

    public LabelPair(String fst, String snd) {
      this.fst = fst;
      this.snd = snd;
    }

    public String getFst() {
      return fst;
    }

    public String getSnd() {
      return snd;
    }
  }

  public void setClass(CLASS classObj) {
    classScope = classObj;
  }

  public boolean inClass() {
    return classScope != null;
  }

  public CLASS getClassScope() {
    return classScope;
  }
  public void resetClass() {
    classScope = null;
  }
}
