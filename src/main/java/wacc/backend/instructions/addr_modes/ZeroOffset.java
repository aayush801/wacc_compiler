package wacc.backend.instructions.addr_modes;

import wacc.backend.registers.Register;

public class ZeroOffset extends AddressingMode {

  private final Register Rn;

  public ZeroOffset(Register rn) {
    Rn = rn;
  }

  @Override
  public String toString() {
    return "[" + Rn + "]";
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof ZeroOffset && ((ZeroOffset) o).Rn.equals(Rn);
  }
}
