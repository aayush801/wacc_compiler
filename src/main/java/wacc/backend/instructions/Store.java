package wacc.backend.instructions;

import wacc.backend.instructions.addr_modes.AddressingMode;
import wacc.backend.registers.Register;

public class Store extends Instruction {

  private final Register Rs;
  private final AddressingMode addressingMode;
  private int size = TYPES.WORD_SIZE;

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

  public Store(ConditionCode code, Register Rs,
      AddressingMode addressingMode) {
    super(code);
    this.Rs = Rs;
    this.addressingMode = addressingMode;
  }

  @Override
  public String toString() {
    String mnemonic = "STR";
    if (size == TYPES.BYTE_SIZE) {
      mnemonic += "B";
    }
    return mnemonic + " " + Rs + ", " + addressingMode;
  }

  public AddressingMode getAddressingMode() {
    return addressingMode;
  }

  public Register getRs() {
    return Rs;
  }
}
