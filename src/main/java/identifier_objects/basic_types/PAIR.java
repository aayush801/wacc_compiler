package identifier_objects.basic_types;

import identifier_objects.TYPE;

public class PAIR extends TYPE {

  public static String name = "pair";
  protected final TYPE first, second;

  public PAIR(TYPE first, TYPE second) {
    super(name);
    this.first = first;
    this.second = second;
  }

  public TYPE getFirst() {
    return first;
  }

  public TYPE getSecond() {
    return second;
  }

  public PAIR() {
    this(null, null);
  }

  public boolean isNullPair(){
    return first == null && second == null;
  }

  @Override
  public String toString(){
    return name + "(" + ((first == null) ? "null" : first.toString()) + ", " + ((second == null) ? "null" : second.toString()) +")";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof PAIR) {
      PAIR pair = (PAIR) o;
      return isNullPair() || pair.isNullPair() || (pair.getFirst().equals(first) && pair.getSecond().equals(second));
    }
    return false;
  }
}
