package error;

public abstract class Error {

  protected String name;

  public Error(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
