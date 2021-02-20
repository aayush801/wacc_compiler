package backend.instructions.addr_modes;

import backend.operands.Immediate;
import backend.registers.Register;

public class ImmediateOffset extends AddressingMode {

  private final Register Rn;
  private final Immediate offset;

  public ImmediateOffset(Register Rn, Immediate offset) {
    this.Rn = Rn;
    this.offset = offset;
  }

  @Override
  public String toString() {
    return (offset.getValue() == 0) ? new ZeroOffset(Rn).toString()
        : "[" + Rn + ", " + offset + "]";
  }
}
