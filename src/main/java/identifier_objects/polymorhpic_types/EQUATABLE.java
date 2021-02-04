package identifier_objects.polymorhpic_types;

import identifier_objects.TYPE;

public class EQUATABLE extends TYPE {

  public EQUATABLE(String name) {
    super(name);
  }

  @Override
  public boolean equals(Object o) {
    if(o instanceof EQUATABLE){
      EQUATABLE type = (EQUATABLE) o;
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
