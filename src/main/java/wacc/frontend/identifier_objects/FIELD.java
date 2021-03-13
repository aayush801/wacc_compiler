package wacc.frontend.identifier_objects;

import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.VARIABLE;
import wacc.middleware.Visibility;

public class FIELD extends VARIABLE {

  private final Visibility visibility;

  public FIELD(TYPE type, Visibility visibility) {
    super(type);
    this.visibility = visibility;
  }

  public FIELD(TYPE type) {
    this(type, Visibility.PUBLIC);
  }

  public Visibility getVisibility() {
    return visibility;
  }
}
