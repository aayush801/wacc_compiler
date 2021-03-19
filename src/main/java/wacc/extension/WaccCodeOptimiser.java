package wacc.extension;

import java.util.ArrayList;
import java.util.List;
import wacc.backend.instructions.Instruction;
import wacc.backend.instructions.Load;
import wacc.backend.instructions.Move;
import wacc.backend.instructions.Store;
import wacc.backend.instructions.arithmetic.Arithmetic;
import wacc.backend.operands.ImmediateNum;

public class WaccCodeOptimiser {

  public static List<Instruction> optimise(List<Instruction> instructions) {
    // simplerAlternative
    instructions = removeRedundantInstructions(instructions);
    instructions = removeLoadAfterStore(instructions);
    return instructions;
  }

  // A load after a store with the same register and same addressing mode is redundant
  private static List<Instruction> removeLoadAfterStore(List<Instruction> instructions) {
    if (instructions.size() == 0) return instructions;
    List<Instruction> improved = new ArrayList<>();
    Instruction curr, prev;
    improved.add(instructions.get(0));
    for (int i = 1; i < instructions.size(); i++) {
      curr = instructions.get(i);

      if (curr instanceof Load) {
        prev = instructions.get(i - 1);

        if (prev instanceof Store) {
          if (((Store) prev).getRs().equals(((Load) curr).getRn())
              && ((Store) prev).getAddressingMode().equals(((Load) curr).getAddressingMode())) {
            continue;
          }
        }
      }
      improved.add(curr);
    }

    return improved;
  }

  // Removed redundant instructions
  private static List<Instruction> removeRedundantInstructions(List<Instruction> instructions) {
    List<Instruction> improved = new ArrayList<>();
    for (Instruction instruction : instructions) {

      // Register move to itself is ignored
      if (!isRedundant(instruction)) {
        improved.add(instruction);
      }
    }

    return improved;
  }

  private static boolean isRedundant(Instruction instruction) {
    if (instruction instanceof Move) {
      return isRedundantMove((Move) instruction);
    } else if (instruction instanceof Arithmetic) {
      return isRedundantArithmetic((Arithmetic) instruction);
    }
    return false;
  }

  // Move from register to itself is redundant
  private static boolean isRedundantMove(Move mov) {
    return !mov.isNegated() && mov.getRd().equals(mov.getOperand());
  }

  // Removed redundant instructions
  private static boolean isRedundantArithmetic(Arithmetic instruction) {
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
