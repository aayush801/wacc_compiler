package wacc.backend.instructions.addr_modes;

public class Address extends AddressingMode {

  private final String address;

  public Address(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "=" + address;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Address && ((Address) o).address.equals(address);
  }
}
