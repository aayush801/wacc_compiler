package backend.operands;

public class ImmediateNumASR extends Operand {

  private final Operand op;
  private final Integer shift;

  public ImmediateNumASR(Operand op, Integer shift) {
    this.shift = shift;
    this.op = op;
  }

  @Override
  public String toString() {
    return op + ", ASR #" + shift;
  }

}
