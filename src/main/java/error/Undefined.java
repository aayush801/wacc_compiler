package error;

public class Undefined extends WaccError{
  private final String identifier;
  public Undefined(String identifier) {
    super("undefined");
    this.identifier = identifier;
  }

  @Override
  public String toString() {
    return "\"" + identifier + "\" is undefined";
  }

}
