package backend.instructions;

import backend.instructions.amodes.AddressingMode;
import backend.registers.Register;

public class Load extends Instruction {

  private final Register Rn;
  private final AddressingMode addrMode;

  public Load(Register Rn, AddressingMode addrMode) {
    this.Rn = Rn;
    this.addrMode = addrMode;
  }

  @Override
  public String toString() {
    return "LDR" + " " + Rn + ", " + addrMode;
  }
}
