package backend.instructions;

import backend.instructions.addr_modes.AddressingMode;
import backend.registers.Register;

public class Load extends Instruction {

  private final Register Rn;
  private final AddressingMode addressingMode;

  private final boolean registerByte;
  private final boolean signed;

  public Load(Register Rn, AddressingMode addressingMode, boolean registerByte, boolean signed) {
    this(ConditionCode.NONE, Rn, addressingMode, registerByte, signed);
  }


  public Load(ConditionCode conditionCode, Register Rn, AddressingMode addressingMode, boolean registerByte, boolean signed) {
    super(conditionCode);
    this.Rn = Rn;
    this.addressingMode = addressingMode;
    this.registerByte = registerByte;
    this.signed = signed;
  }

  public Load(Register Rn, AddressingMode addressingMode) {
    this(Rn, addressingMode, false, false);
  }

  @Override
  public String toString() {

    String mnemonic = "LDR";
    if (signed) {
      mnemonic += "S";
    }
    if (registerByte) {
      mnemonic += "B";
    }
    return mnemonic + code + " " + Rn + ", " + addressingMode;

  }
}
