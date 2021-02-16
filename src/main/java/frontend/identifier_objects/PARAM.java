package frontend.identifier_objects;


public class PARAM extends IDENTIFIER {

  private final TYPE type;

  public PARAM(TYPE type) {
    super("param");
    this.type = type;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public String toString() {
    return name;
  }

}
