package backend.instructions;

import backend.instructions.addr_modes.AddressingMode;
import backend.registers.Register;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;

public class Store extends Instruction {

  private final Register Rs;
  private final AddressingMode addressingMode;
  private TYPE type = new INT();

  public Store(Register Rs, AddressingMode addressingMode, TYPE type) {
    this(ConditionCode.NONE, Rs, addressingMode, type);
  }

  public Store(Register Rs, AddressingMode addressingMode) {
    this(ConditionCode.NONE, Rs, addressingMode);
  }


  public Store(ConditionCode code, Register Rs, AddressingMode addressingMode,
      TYPE type) {
    super(code);
    this.Rs = Rs;
    this.addressingMode = addressingMode;
    this.type = type;
  }

  public Store(ConditionCode code, Register Rs, AddressingMode addressingMode) {
    super(code);
    this.Rs = Rs;
    this.addressingMode = addressingMode;
  }

  @Override
  public String toString() {
    String mnemonic = "STR";
    if (type instanceof CHAR || type instanceof BOOL) {
      mnemonic += "B";
    }
    return mnemonic + " " + Rs + ", " + addressingMode;
  }
}
