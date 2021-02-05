package error;

public class NotAFunction extends ERROR {

  protected String identifier;

  public NotAFunction(String identifer) {
    super("NotAFunction");
    this.identifier = identifer;
  }

  @Override
  public String toString() {
    return "\"" + identifier + "\" is not a function";
  }

}
