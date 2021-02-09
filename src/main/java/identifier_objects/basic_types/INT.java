package identifier_objects.basic_types;

import identifier_objects.TYPE;
import javax.print.attribute.standard.NumberUp;

public class INT extends TYPE {

  public static String name = "int";
  protected final int min, max;

  public INT(int min, int max) {
    super(name);
    this.min = min;
    this.max = max;
  }

  public INT() {
    this(Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public boolean check(String x) {
    try {
      int integer = Integer.parseInt(x);
      return min <= integer && integer <= max;
    }catch (NumberFormatException e){
      return false;
    }
  }

}
