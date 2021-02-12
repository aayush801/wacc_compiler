package identifier_objects.basic_types;

import identifier_objects.TYPE;

public class CHAR extends TYPE {

  public static String name = "char";
  protected final int min, max;

  public CHAR(int min, int max) {
    super(name);
    this.min = min;
    this.max = max;
  }

  public CHAR() {
    this(0, 255);
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof CHAR;
  }
}
