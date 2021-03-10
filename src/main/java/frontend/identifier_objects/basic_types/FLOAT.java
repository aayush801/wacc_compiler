package frontend.identifier_objects.basic_types;

import frontend.identifier_objects.TYPE;

public class FLOAT extends TYPE {

  public static String name = "float";

  @Override
  public boolean equals(Object o) {
    return o instanceof FLOAT;
  }

  @Override
  public int getSize() {
    return 4;
  }

}
