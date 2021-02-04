package identifier_objects;

public class PARAM extends TYPE {

  public TYPE type;

  public PARAM(TYPE type) {
    super("param");
    this.type = type;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public String toString(){
   return "undefined";
  }

}
