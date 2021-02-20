package backend.instructions;

import backend.instructions.addr_modes.AddressingMode;
import backend.operands.Operand;
import backend.registers.Register;

public class Store extends Instruction {
  private final Register Rs;
  private final AddressingMode addressingMode;

  public Store(Register Rs, AddressingMode addressingMode) {
    this.Rs = Rs;
    this.addressingMode = addressingMode;
  }

  @Override
  public String toString() {
    return "STR" + " " + Rs + ", " + addressingMode;
  }
}
