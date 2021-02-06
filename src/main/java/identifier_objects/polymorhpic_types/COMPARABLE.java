package identifier_objects.polymorhpic_types;

import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;

public class COMPARABLE extends EXPR {

  @Override
  public String toString(){
    if(name == null) {
      return CHAR.name + " or " + INT.name;
    }else{
      return name;
    }
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof CHAR || o instanceof INT) && super.equals(o);
  }
}
