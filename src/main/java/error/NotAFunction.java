package error;

public class NotAFunction extends WaccError {

  public NotAFunction(String identifier) {
    super("\"" + identifier + "\" is not a function");
  }
}
