package identifier_objects.basic_types;

import identifier_objects.TYPE;

public class CHAR extends TYPE {


  protected final int min, max;

  public CHAR(int min, int max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public String toString(){
    return "char";
  }
}
