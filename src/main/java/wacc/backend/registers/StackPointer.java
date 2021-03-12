package wacc.backend.registers;

import wacc.backend.instructions.Instruction;
import wacc.backend.instructions.arithmetic.Arithmetic;
import wacc.backend.instructions.arithmetic.ArithmeticOpcode;
import wacc.backend.operands.ImmediateNum;
import wacc.frontend.identifier_objects.STACK_OBJECT;
import java.util.ArrayList;
import java.util.List;

public class StackPointer extends Register {

  private int freePtr = 0, stackPtr = 0;

  public StackPointer() {
    super(13);
  }

  public void push(STACK_OBJECT varObj) {
    // decrement free pts by variable size
    freePtr -= varObj.getType().getSize();
    varObj.setStackAddress(freePtr);

    // if the free ptr is below the stack ptr
    // then decrement stack ptr aswell
    if (freePtr <= stackPtr) {

      stackPtr = freePtr;

    }

  }

  public void pop(STACK_OBJECT varObj) {
    int popSize = varObj.getType().getSize();

    // if the fre ptr sits at the same level
    // as the stack ptr, then increment both pointers
    if (freePtr == stackPtr) {

      stackPtr += popSize;

    }

    freePtr += popSize;
  }

  public List<Instruction> decrement(int size) {
    stackPtr -= size;
    List<Instruction> instructions = new ArrayList<>();

    // decrement the stack pointer in batches of the largest immediate instruction size
    for (; size > ImmediateNum.MAX_SIZE; size -= ImmediateNum.MAX_SIZE) {
      instructions.add(
          new Arithmetic(ArithmeticOpcode.SUB, this, this,
              new ImmediateNum(ImmediateNum.MAX_SIZE), false));
    }

    // decrement the stack pointer by the overflow size of the stack
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

    // increment the stack pointer in batches of the largest immediate instruction size
    for (; size > ImmediateNum.MAX_SIZE; size -= ImmediateNum.MAX_SIZE) {
      instructions.add(
          new Arithmetic(ArithmeticOpcode.ADD, this, this,
              new ImmediateNum(ImmediateNum.MAX_SIZE), false));
    }

    // increment the stack pointer by the overflow size of the stack
    if (size >= 0) {
      instructions.add(
          new Arithmetic(ArithmeticOpcode.ADD, this, this,
              new ImmediateNum(size), false));
    }

    return instructions;
  }

  public void setState(int stackPtr, int freePtr) {
    this.stackPtr = stackPtr;
    this.freePtr = freePtr;
  }

  public int getStackPtr() {
    return stackPtr;
  }

  public int getFreePtr() {
    return freePtr;
  }

  public int calculateOffset(int address) {
    return address - stackPtr;
  }


  @Override
  public String toString() {
    return "sp";
  }
}
