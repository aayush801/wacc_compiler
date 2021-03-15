package wacc.backend.instructions.addr_modes;

public class ImmediateAddress extends AddressingMode {

  private final int address;

  public ImmediateAddress(int n) {
    this.address = n;
  }

  @Override
  public String toString() {
    return "=" + address;
  }

  public int getAddress() {
    return address;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof ImmediateAddress &&
        ((ImmediateAddress) o).address == address;
  }
}
