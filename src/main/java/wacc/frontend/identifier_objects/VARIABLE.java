package wacc.frontend.identifier_objects;


import wacc.middleware.Visibility;

public class VARIABLE extends STACK_OBJECT {

  public VARIABLE(TYPE type) {
    super("variable", type);
  }

  public VARIABLE(String name, TYPE type) {
    super(name, type);
  }

}
