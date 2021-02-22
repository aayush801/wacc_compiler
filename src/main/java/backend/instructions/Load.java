package backend.instructions;

import backend.instructions.addr_modes.AddressingMode;
import backend.registers.Register;

public class Load extends Instruction {

  private final Register Rn;
  private final AddressingMode addressingMode;


  public Load(Register Rn, AddressingMode addressingMode, ConditionCode code) {
    super(code);
    this.Rn = Rn;
    this.addressingMode = addressingMode;
  }

  public Load(Register Rn, AddressingMode addressingMode) {
    this(Rn, addressingMode, ConditionCode.NONE);
  }

  @Override
  public String toString() {
    return  "LDR " + Rn + ", " + addressingMode;
  }
}
