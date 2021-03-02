package backend.instructions.addr_modes;

import backend.operands.ImmediateNum;
import backend.registers.Register;

public class ImmediateOffset extends AddressingMode {

  private final Register Rn;
  private final ImmediateNum offset;
  private boolean preIndexed = false;

  public ImmediateOffset(Register Rn, ImmediateNum offset) {
    this.Rn = Rn;
    this.offset = offset;
  }

  public ImmediateOffset(Register Rn, ImmediateNum offset,
      boolean preIndexed) {
    this(Rn, offset);
    this.preIndexed = preIndexed;
  }

  @Override
  public String toString() {
    return (offset.getValue() == 0) ? new ZeroOffset(Rn).toString()
        : "[" + Rn + ", " + offset + "]" + (preIndexed ? "!" : "");
  }
}
