package backend.instructions.floating_point;

import backend.instructions.ConditionCode;
import backend.instructions.Move;
import backend.operands.Operand;
import backend.registers.Register;

public class MoveFloat extends Move {


  public MoveFloat(Register Rd, backend.operands.Operand Operand) {
    super(Rd, Operand);
  }

  public MoveFloat(Register Rd, backend.operands.Operand Operand, boolean isNegated) {
    super(Rd, Operand, isNegated);
  }

  public MoveFloat(ConditionCode code, Register Rd,
      backend.operands.Operand Operand,
      boolean flags) {
    super(code, Rd, Operand, flags);
  }

  @Override
  public String toString() {
    return "VMOV" + getFlags() + ".F32" + getRd() + getOperand();
  }
}
