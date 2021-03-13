package wacc.frontend.identifier_objects;

import wacc.frontend.identifier_objects.FUNCTION;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.Visibility;

public class METHOD extends FUNCTION {

  private final Visibility visibility;

  public METHOD(TYPE type, Visibility visibility) {
    super(type);
    this.visibility = visibility;
  }

  public METHOD(TYPE type) {
    this(type, Visibility.PUBLIC);
  }

  public Visibility getVisibility() {
    return visibility;
  }
}
