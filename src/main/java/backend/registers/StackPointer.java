package backend.registers;

import backend.instructions.Instruction;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.operands.ImmediateNum;
import frontend.identifier_objects.STACK_OBJECT;
import frontend.identifier_objects.VARIABLE;

public class StackPointer extends Register {

  private int freePtr = 0, stackPtr = 0;

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
    if (freePtr + popSize > stackPtr && freePtr <= stackPtr) {
      stackPtr = freePtr + popSize;
    }
    freePtr += popSize;
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
    stackPtr = freePtr = 0;
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
