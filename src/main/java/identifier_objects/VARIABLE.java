package identifier_objects;

public class VARIABLE extends TYPE {

  TYPE type;

  public VARIABLE(TYPE type) {
    super("variable");
    this.type = type;
  }

  public TYPE getType() {
    return type;
  }
}
