package error;

public class NotAFunction {

  protected String identifier;

  public NotAFunction(String identifer) {
    this.identifier = identifer;
  }

  @Override
  public String toString() {
    return "\"" + identifier + "\" is not a function";
  }

}
