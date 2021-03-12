package wacc.backend.operands;

public class ImmediateShift extends Operand {

  private final Operand op;
  private final Integer shift;
  private boolean isLeftShift = false;

  public ImmediateShift(Operand op, Integer shift, boolean isLeftShift) {
    this.shift = shift;
    this.op = op;
    this.isLeftShift = isLeftShift;
  }

  @Override
  public String toString() {
    return op + ", " + ((isLeftShift) ? "LSL" : "ASR") + " #" + shift;
  }

}
