package wacc.backend.instructions;

import wacc.backend.instructions.addr_modes.AddressingMode;
import wacc.backend.registers.Register;

public class Load extends Instruction {

  private final Register Rn;
  private final AddressingMode addressingMode;
  private int size = TYPES.WORD_SIZE;

  public Load(Register Rn, AddressingMode addressingMode, int size) {
    this(ConditionCode.NONE, Rn, addressingMode, size);
  }

  public Load(ConditionCode conditionCode, Register Rn,
      AddressingMode addressingMode, int size) {
    super(conditionCode);
    this.Rn = Rn;
    this.addressingMode = addressingMode;
    this.size = size;
  }

  public Load(ConditionCode conditionCode, Register Rn,
      AddressingMode addressingMode) {
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

    return "LDR"
        + (size == TYPES.BYTE_SIZE ? "SB" : "")
        + code + " " + Rn + ", " + addressingMode;

  }
}
