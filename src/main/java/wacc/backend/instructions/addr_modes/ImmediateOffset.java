package wacc.backend.instructions.addr_modes;

import java.util.Objects;
import wacc.backend.operands.ImmediateNum;
import wacc.backend.registers.Register;

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

  @Override
  public boolean equals(Object o) {
    return o instanceof ImmediateOffset &&
        preIndexed == ((ImmediateOffset) o).preIndexed &&
        Rn.equals(((ImmediateOffset) o).Rn) &&
        offset.equals(((ImmediateOffset) o).offset);
  }
}
