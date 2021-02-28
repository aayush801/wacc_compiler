package backend.instructions;

import backend.instructions.addr_modes.AddressingMode;
import backend.registers.Register;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;

public class Load extends Instruction {

  private final Register Rn;
  private final AddressingMode addressingMode;

  private TYPE type = new INT();

  public Load(Register Rn, AddressingMode addressingMode, TYPE type) {
    this(ConditionCode.NONE, Rn, addressingMode, type);
  }


  public Load(ConditionCode conditionCode, Register Rn, AddressingMode addressingMode,
      TYPE type) {
    super(conditionCode);
    this.Rn = Rn;
    this.addressingMode = addressingMode;
    this.type = type;
  }

  public Load(ConditionCode conditionCode, Register Rn, AddressingMode addressingMode) {
    super(conditionCode);
    this.Rn = Rn;
    this.addressingMode = addressingMode;
  }

  public Load(Register Rn, AddressingMode addressingMode) {
    this.Rn = Rn;
    this.addressingMode = addressingMode;
  }

  @Override
  public String toString() {

    String mnemonic = "LDR";
    if (type instanceof CHAR || type instanceof BOOL) {
      mnemonic += "SB";
    }

    return mnemonic + code + " " + Rn + ", " + addressingMode;

  }
}
