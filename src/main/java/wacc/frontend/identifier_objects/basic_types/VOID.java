package wacc.frontend.identifier_objects.basic_types;

import wacc.frontend.identifier_objects.TYPE;

public class VOID extends TYPE {

  private static final String name = "void";

  public VOID() {
    super(name);
  }

  @Override
  public boolean equals(Object o) {
    return false;
  }

}
