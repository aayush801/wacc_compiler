package backend.registers;

import backend.instructions.Instruction;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.operands.ImmediateNum;
import frontend.identifier_objects.VARIABLE;

public class StackPointer extends Register {

  private int freePtr = 0;
  private int stackPtr = 0;

  public StackPointer() {
    super(13);
  }

  public int push(VARIABLE varObj) {
    freePtr -= varObj.getType().getSize();
    varObj.setStackAddress(freePtr);
    if (freePtr < stackPtr) {
      stackPtr = freePtr;
    }
    return freePtr;
  }

  public int pop(VARIABLE varObj) {
    freePtr += varObj.getType().getSize();
    if (freePtr > stackPtr) {
      stackPtr = freePtr;
    }
    return freePtr;
  }

  public Instruction decrement(int size) {
    stackPtr -= size;
    return new Arithmetic(ArithmeticOpcode.SUB, this, this,
        new ImmediateNum(size), false);
  }


  public Instruction increment(int size) {
    stackPtr += size;
    return new Arithmetic(ArithmeticOpcode.ADD, this, this,
        new ImmediateNum(size), false);
  }

  public void reset() {
    stackPtr = 0;
    freePtr = 0;
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
