package identifier_objects;

public abstract class TYPE extends IDENTIFIER {

  public TYPE(String name) {
    super(name);
  }

  public TYPE getType(){
    return this;
  }
}
