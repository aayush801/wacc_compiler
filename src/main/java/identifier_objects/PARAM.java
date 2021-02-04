package identifier_objects;

public class PARAM extends TYPE {

  public TYPE type;

  public PARAM(TYPE type) {
    this.type = type;
  }

  @Override
  public TYPE getType() {
    return this.type;
  }

  @Override
  public String toString(){
   return "undefined";
  }

}
