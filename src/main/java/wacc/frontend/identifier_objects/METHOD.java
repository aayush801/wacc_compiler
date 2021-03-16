package wacc.frontend.identifier_objects;

import wacc.frontend.identifier_objects.FUNCTION;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.Visibility;

public class METHOD extends FUNCTION {

  private final Visibility visibility;

  public METHOD(FUNCTION funcObj, Visibility visibility)
  {
    super(funcObj.returnType);
    formals = funcObj.formals;
    setST(funcObj.getST());
    this.visibility = visibility;
  }

  public Visibility getVisibility() {
    return visibility;
  }
}
