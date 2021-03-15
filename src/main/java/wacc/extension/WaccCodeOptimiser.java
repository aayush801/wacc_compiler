package wacc.extension;

import static wacc.backend.instructions.arithmetic.ArithmeticOpcode.ADD;
import static wacc.backend.instructions.arithmetic.ArithmeticOpcode.SMULL;
import static wacc.backend.instructions.arithmetic.ArithmeticOpcode.SUB;

import java.util.Arrays;
import wacc.backend.instructions.Branch;
import wacc.backend.instructions.Instruction;
import wacc.backend.instructions.Load;
import wacc.backend.instructions.Move;
import wacc.backend.instructions.Store;
import wacc.backend.instructions.addr_modes.ImmediateAddress;
import wacc.backend.instructions.arithmetic.Arithmetic;
import wacc.backend.operands.ImmediateNum;
import java.util.ArrayList;
import java.util.List;

public class WaccCodeOptimiser {

  public static List<Instruction> optimise(List<Instruction> instructions) {
    //simplerAlternative
    instructions = removeRedundantInstructions(instructions);
    instructions = removeLoadAfterStore(instructions);
    //instructions = reduceArithmeticOperations(instructions);
    return instructions;
  }

  // A load after a store with the same register and same addressing mode is redundant
  private static List<Instruction> removeLoadAfterStore(List<Instruction> instructions) {
    List<Instruction> improved = new ArrayList<>();
    Instruction curr, prev;
    for (int i = 1; i < instructions.size(); i++) {
      curr = instructions.get(i);
      if (curr instanceof Load) {
        prev = instructions.get(i - 1);
        if (prev instanceof Store) {
            if (((Store) prev).getRs().equals(((Load) curr).getRn())
                && ((Store) prev).getAddressingMode()
            .equals(((Load) curr).getAddressingMode())){
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

  // Combine successive arithmetic instructions into a single one
  private static List<Instruction> reduceArithmeticOperations(List<Instruction> instructions) {
    List<Instruction> improved = new ArrayList<>();
    Instruction curr;
    ArrayList opcodes = new ArrayList<>(Arrays.asList(ADD, SUB, SMULL));
    // If a number is loaded into a register and is being incremented, decremented etc.
    // in the following instructions using immediate values then this can be replaced
    // by a single load instruction with the final value as immediate address
    for (int i = 0; i < instructions.size() - 1; i++) {
      curr = instructions.get(i);
      if (curr instanceof Load &&
          ((Load) curr).getAddressingMode() instanceof ImmediateAddress) {

        int value = ((ImmediateAddress) ((Load) curr)
            .getAddressingMode()).getAddress();

        i++;
        Instruction next = instructions.get(i);
        int n;
        while (i < instructions.size() - 1
            && next instanceof Arithmetic
            && opcodes.contains(((Arithmetic) next).getOpcode())
            && ((Arithmetic) next).getOperand() instanceof ImmediateNum
            && ((Arithmetic) next).getRn() == ((Load) curr).getRn()
            && ((Arithmetic) next).getRn() == ((Arithmetic) next).getRd()) {
          next = instructions.get(i);
          n = (((ImmediateNum) ((Arithmetic) next).getOperand()).getValue());
          // If this causes an integer overflow then no more simplification can be done so break
          try {
            switch (((Arithmetic) next).getOpcode()) {
              case ADD:
                value += n;
                break;
              case SUB:
                value -= n;
                break;
              case SMULL:
                value *= n;
                break;
            }
          } catch (ArithmeticException e) {
            break;
          }
          next = instructions.get(++i);
          if (next instanceof Branch &&
              ((Branch) next).getLabel().equals("p_throw_overflow_error")) {
            next = instructions.get(++i);
          }
        }
        i--;
        improved.add(new Load(((Load) curr).getRn(), new ImmediateAddress(value)));
      } else {
        improved.add(curr);
      }
    }

    return improved;
  }
}
