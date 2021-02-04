package identifier_objects.polymorhpic_types;

import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;

public class COMPARABLE extends EQUATABLE {

  public COMPARABLE(String name) {
    super(name);
  }

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
    if(o instanceof COMPARABLE){
      COMPARABLE type = (COMPARABLE) o;
      if(name != null){
        return name.equals(type.getName());
      }else {
        name = type.getName();
        return true;
      }
    }
    return false;
  }
}
