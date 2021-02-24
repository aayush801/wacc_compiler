package backend.operands;

public class ImmediateNumLSL extends Operand {

  private final Operand op;
  private final Integer shift;

  public ImmediateNumLSL(Operand op, Integer shift) {
    this.shift = shift;
    this.op = op;
  }

  @Override
  public String toString() {
    return op + ", LSL #" + shift;
  }
}
