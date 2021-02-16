package backend.instructions;

public class Branch extends Instruction {

  private final String label;
  boolean isLinkFlagSet = false;

  public Branch(String label) {
    this.label = label;
  }

  public Branch(String label, boolean isLinkFlagSet) {
    this(label);
    this.isLinkFlagSet = isLinkFlagSet;
  }

  @Override
  public String toString() {
    return "B" + (isLinkFlagSet ? "L" : "") + " " + label;
  }
}
