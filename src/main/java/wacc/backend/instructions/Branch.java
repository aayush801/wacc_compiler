package wacc.backend.instructions;

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

  public Branch(ConditionCode code, String label, boolean isLinkFlagSet) {
    super(code);
    this.label = label;
    this.isLinkFlagSet = isLinkFlagSet;
  }

  @Override
  public String toString() {
    return "B" + (isLinkFlagSet ? "L" : "") + getFlags() + " " + label;
  }

  public String getLabel() {
    return label;
  }
}
