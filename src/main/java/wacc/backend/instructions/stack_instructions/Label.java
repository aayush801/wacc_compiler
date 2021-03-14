package wacc.backend.instructions.stack_instructions;

import wacc.backend.instructions.Instruction;

public class Label extends Instruction {

  private static int INDEX = 0;
  private final String name;

  public static Label getUnusedLabel() {
    return new Label("L" + INDEX++);
  }

  public Label(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String toString() {
    return name + ":";
  }

}
