package extension;

import backend.instructions.Instruction;
import backend.instructions.Move;
import backend.instructions.arithmetic.Arithmetic;
import backend.operands.ImmediateNum;
import java.util.ArrayList;
import java.util.List;

public class WaccCodeOptimiser {

  public List<Instruction> optimise(List<Instruction> instructions) {
    return peep(instructions);
  }

  private List<Instruction> peep(List<Instruction> instructions) {
    List<Instruction> improved = new ArrayList<>();
    for (Instruction instruction : instructions) {

      // Register move to itself is ignored
      if (!isRedundant(instruction)) {
        improved.add(instruction);
      }
    }

    return improved;
  }

  private boolean isRedundant(Instruction instruction) {
    if (instruction instanceof Move) {
      return isRedundant((Move) instruction);
    }
    return false;
  }

  private boolean isRedundant(Move mov) {
    return mov.getRd().equals(mov.getOperand());
  }

  private boolean isRedundant(Arithmetic instruction) {
    // If operand register is destination
    if (instruction.getRd().equals(instruction.getRn())) {
      switch (instruction.getOpcode()) {
        case SUB:
        case ADD:
          // Adding or subtracting 0 has no effect
          if (instruction.getOperand() instanceof ImmediateNum) {
            if (((ImmediateNum) instruction.getOperand()).getValue() == 0) {
              return true;
            }
          }
          break;

        // Multiply by one has no effect
        case SMULL:
          if (instruction.getOperand() instanceof ImmediateNum) {
            if (((ImmediateNum) instruction.getOperand()).getValue() == 1) {
              return true;
            }
          }
          break;

        default:
          break;
      }
    }

    return false;
  }

}
