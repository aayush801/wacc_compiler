package error;

public abstract class WaccError {

  protected String name;

  public WaccError(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if(o instanceof String) {
      return toString().equals(o.toString());
    }
    return false;
  }
}
