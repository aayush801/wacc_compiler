package frontend.identifier_objects.basic_types;

import frontend.identifier_objects.TYPE;

public class BOOL extends TYPE {

  public static String name = "bool";

  public BOOL() {
    super(name);
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof BOOL;
  }

  @Override
  public int getSize(){
    return 1;
  }
}
