package error;

public abstract class ERROR {

  protected String name;

  public ERROR(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    return toString().equals(o.toString());
  }
}
