package identifier_objects;

public class VARIABLE implements IDENTIFIER {

  TYPE type;

  public VARIABLE(TYPE type) {
    this.type = type;
  }

  @Override
  public TYPE getType() {
    return type;
  }
}
