package backend.instructions.amodes;

import backend.registers.Register;

public class ZeroOffset extends AddressingMode{
  private final Register Rn;

  public ZeroOffset(Register rn) {
    Rn = rn;
  }

  @Override
  public String toString() {
    return "[" + Rn + "]";
  }
}