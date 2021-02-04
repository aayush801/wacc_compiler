package identifier_objects.basic_types;

import identifier_objects.TYPE;

public class PAIR extends TYPE {

  protected TYPE first, second;

  public PAIR(TYPE first, TYPE second) {
    this.first = first;
    this.second = second;
  }

  public PAIR() {
    this(null, null);
  }


  @Override
  public String toString(){
    return "pair";
  }
}
