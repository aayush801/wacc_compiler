package backend.instructions;

import backend.instructions.addr_modes.AddressingMode;
import backend.registers.Register;

public class Store extends Instruction {

  private final Register Rs;
  private final AddressingMode addressingMode;
  private final boolean registerByte;

  public Store(Register Rs, AddressingMode addressingMode) {
    this(ConditionCode.NONE, Rs, addressingMode, false);
  }

  public Store(ConditionCode code, Register Rs, AddressingMode addressingMode) {
    this(code, Rs, addressingMode, false);
  }

  public Store(ConditionCode code, Register Rs, AddressingMode addressingMode,
      boolean registerByte) {
    super(code);
    this.Rs = Rs;
    this.addressingMode = addressingMode;
    this.registerByte = registerByte;
  }

  @Override
  public String toString() {
    String mnemonic = "STR";
    if (registerByte) {
      mnemonic += "B";
    }
    return mnemonic + " " + Rs + ", " + addressingMode;
  }
}
