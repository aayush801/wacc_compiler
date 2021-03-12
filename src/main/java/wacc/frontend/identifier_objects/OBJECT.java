package wacc.frontend.identifier_objects;

public class OBJECT extends STACK_OBJECT {

  public OBJECT(CLASS classObj) {
    super("object", classObj);
  }

  public int getSize() {
    return type.getSize();
  }
}
