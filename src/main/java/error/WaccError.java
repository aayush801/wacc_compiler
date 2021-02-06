package error;

public abstract class WaccError {

  protected String message;

  public WaccError(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if(o instanceof WaccError) {
      return toString().equals(o.toString());
    }
    return false;
  }
}
