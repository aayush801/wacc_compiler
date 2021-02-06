package identifier_objects.polymorhpic_types;

import identifier_objects.TYPE;

public class EXPR extends TYPE {

  public EXPR() {
    super(null);
  }

  @Override
  public boolean equals(Object o) {
    if(o instanceof TYPE){
      TYPE type = (TYPE) o;
      if(name != null){
        return name.equals(type.getName());
      }else {
        //name = type.getName();
        return true;
      }
    }
    return false;
  }

}
