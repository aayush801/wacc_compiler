package error;

public class Undefined extends WaccError{

  public Undefined(String identifier) {
    super("\"" + identifier + "\" is undefined");
  }
}
