package backend.instructions;

import backend.instructions.addr_modes.AddressingMode;
import backend.registers.Register;

public class Store extends Instruction {

  private final Register Rs;
  private final AddressingMode addressingMode;
  private int size = 4; // size in bytes

  public Store(Register Rs, AddressingMode addressingMode, int size) {
    this(ConditionCode.NONE, Rs, addressingMode, size);
  }

  public Store(Register Rs, AddressingMode addressingMode) {
    this(ConditionCode.NONE, Rs, addressingMode);
  }


  public Store(ConditionCode code, Register Rs, AddressingMode addressingMode,
      int size) {
    super(code);
    this.Rs = Rs;
    this.addressingMode = addressingMode;
    this.size = size;
  }

  public Store(ConditionCode code, Register Rs, AddressingMode addressingMode) {
    super(code);
    this.Rs = Rs;
    this.addressingMode = addressingMode;
  }

  @Override
  public String toString() {
    String mnemonic = "STR";
    if (size == 1) {
      mnemonic += "B";
    }
    return mnemonic + " " + Rs + ", " + addressingMode;
  }
}
