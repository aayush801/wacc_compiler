package error;

public class VariableNotFound extends WaccError {

  public VariableNotFound(String identifier) {
    super("Variable \"" + identifier + "\" is undefined");
  }
}
