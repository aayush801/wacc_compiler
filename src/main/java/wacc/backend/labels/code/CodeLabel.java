package wacc.backend.labels.code;

import wacc.backend.instructions.Instruction;
import wacc.backend.instructions.stack_instructions.LabelledInstruction;
import wacc.backend.labels.Label;
import wacc.extension.WaccCodeOptimiser;
import java.util.List;

public class CodeLabel extends Label {

  protected List<Instruction> instructions;

  public CodeLabel(String name, List<Instruction> instructions) {
    super(name);
    this.instructions = instructions;
  }

  public List<Instruction> optimised() {

    return WaccCodeOptimiser.optimise(instructions);

  }


  @Override
  public String toString() {

    StringBuilder builder = new StringBuilder(name);

    builder.append(": \n");

    // optimise instructions at the machine level
    optimised().forEach(i -> {

          if (!(i instanceof LabelledInstruction)) {

            // Labelled instructions are exempt from being indented
            builder.append("\t");

          }

          builder.append(i).append("\n");

        }
    );

    return builder.toString();
  }
}
