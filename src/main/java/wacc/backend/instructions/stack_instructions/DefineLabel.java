package wacc.backend.instructions.stack_instructions;

import wacc.backend.instructions.Instruction;

public class DefineLabel extends Instruction {

  private static int INDEX = 0;
  private final String name;

  public static DefineLabel getUnusedLabel() {
    return new DefineLabel("L" + INDEX++);
  }

  private DefineLabel(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String toString() {
    return name + ":";
  }

}
