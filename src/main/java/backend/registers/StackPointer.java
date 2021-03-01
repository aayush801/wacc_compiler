package backend.registers;

import backend.instructions.Instruction;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.operands.ImmediateNum;
import frontend.identifier_objects.STACK_OBJECT;
import java.util.ArrayList;
import java.util.List;

public class StackPointer extends Register {

  private int freePtr = 0, stackPtr = 0, savedStackPtr = 0, savedFreePtr = 0;

  public StackPointer() {
    super(13);
  }

  public int push(STACK_OBJECT varObj) {
    freePtr -= varObj.getType().getSize();
    varObj.setStackAddress(freePtr);
    if (freePtr <= stackPtr) {
      stackPtr = freePtr;
    }
    return freePtr;
  }

  public int pop(STACK_OBJECT varObj) {
    int popSize = varObj.getType().getSize();
    if (freePtr == stackPtr) {
      stackPtr += popSize;
    }
    freePtr += popSize;
    return freePtr;
  }

  public List<Instruction> decrement(int size) {
    stackPtr -= size;
    List<Instruction> instructions = new ArrayList<>();

    for (; size > ImmediateNum.MAX_SIZE; size -= ImmediateNum.MAX_SIZE) {
      instructions.add(
          new Arithmetic(ArithmeticOpcode.SUB, this, this,
              new ImmediateNum(ImmediateNum.MAX_SIZE), false));
    }

    if (size >= 0) {
      instructions.add(
          new Arithmetic(ArithmeticOpcode.SUB, this, this,
              new ImmediateNum(size), false));

    }

    return instructions;
  }


  public List<Instruction> increment(int size) {
    stackPtr += size;

    List<Instruction> instructions = new ArrayList<>();

    for (; size > ImmediateNum.MAX_SIZE; size -= ImmediateNum.MAX_SIZE) {
      instructions.add(
          new Arithmetic(ArithmeticOpcode.ADD, this, this,
              new ImmediateNum(ImmediateNum.MAX_SIZE), false));
    }

    if (size >= 0) {
      instructions.add(
          new Arithmetic(ArithmeticOpcode.ADD, this, this,
              new ImmediateNum(size), false));

    }

    return instructions;
  }

  public void store() {
    savedStackPtr = stackPtr;
    savedFreePtr = freePtr;
  }

  public void restore() {
    stackPtr = savedStackPtr;
    freePtr = savedFreePtr;
  }

  public int getStackPtr() {
    return stackPtr;
  }

  public int calculateOffset(int address) {
    return address - stackPtr;
  }


  @Override
  public String toString() {
    return "sp";
  }
}
