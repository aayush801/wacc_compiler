package frontend.identifier_objects.basic_types;

import frontend.identifier_objects.TYPE;

public class STR extends TYPE {

  public static String name = "string";

  public STR() {
    super(name);
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof STR;
  }
}
