package error;

public class VariableNotFound extends WaccError {

  protected String identifier;

  public VariableNotFound(String var) {
    super("VariableNotFound");
    this.identifier = var;
  }

  @Override
  public String toString() {
    return "Variable \"" + identifier + "\" is undefined";
  }
}
