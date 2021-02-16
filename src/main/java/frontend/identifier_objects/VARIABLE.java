package frontend.identifier_objects;


public class VARIABLE extends IDENTIFIER {

  TYPE type;

  public VARIABLE(TYPE type) {
    super("variable");
    this.type = type;
  }

  public TYPE getType() {
    return type;
  }

}
