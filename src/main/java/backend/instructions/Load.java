package backend.instructions;

import backend.instructions.addr_modes.AddressingMode;
import backend.registers.Register;

public class Load extends Instruction {

  private final Register Rn;
  private final AddressingMode addressingMode;
  private final boolean registerByte;

  public Load(Register Rn, AddressingMode addressingMode, boolean registerByte) {
    this.Rn = Rn;
    this.addressingMode = addressingMode;
    this.registerByte = registerByte;
  }

  public Load(Register Rn, AddressingMode addressingMode) {
    this(Rn, addressingMode, false);
  }

  @Override
  public String toString() {
    String mnemonic = "LDR";
    if (registerByte) {
      mnemonic += "B";
    }
    return mnemonic + " " + Rn + ", " + addressingMode;
  }
}
