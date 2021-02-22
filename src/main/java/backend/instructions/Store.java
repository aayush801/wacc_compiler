package backend.instructions;

import backend.instructions.addr_modes.AddressingMode;
import backend.registers.Register;

public class Store extends Instruction {

  private final Register Rs;
  private final AddressingMode addressingMode;

  public Store(Register Rs, AddressingMode addressingMode) {
    super();
    this.Rs = Rs;
    this.addressingMode = addressingMode;
  }

  public Store(ConditionCode code, Register Rs, AddressingMode addressingMode) {
    super(code);
    this.Rs = Rs;
    this.addressingMode = addressingMode;
  }

  @Override
  public String toString() {
    return "STR" + getFLags() + " " + Rs + ", " + addressingMode;
  }
}