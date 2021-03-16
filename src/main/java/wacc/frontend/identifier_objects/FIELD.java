package wacc.frontend.identifier_objects;

import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.VARIABLE;
import wacc.middleware.Visibility;

public class FIELD extends VARIABLE {

  private final Visibility visibility;
  private final int offset;

  public FIELD(TYPE type, Visibility visibility, int offset) {
    super("field", type);
    this.visibility = visibility;
    this.type = type;
    this.offset = offset;
  }

  public int getOffset() {
    return offset;
  }

  public Visibility getVisibility() {
    return visibility;
  }
}
