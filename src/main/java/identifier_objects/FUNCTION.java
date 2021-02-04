package identifier_objects;

import symbol_table.SymbolTable;

public class FUNCTION extends TYPE {

  public final TYPE returnType;
  public final SymbolTable ST;
  public final PARAM[] formals;

  public FUNCTION(TYPE returnType, PARAM[] formals, SymbolTable ST) {
    super("function");
    this.returnType = returnType;
    this.ST = new SymbolTable(ST);
    this.formals = formals;
  }

  public TYPE getType() {
    return returnType;
  }

  public boolean equals(Object o) {
    if (o instanceof FUNCTION) {
      FUNCTION func = (FUNCTION) o;
      if (func.formals.length != formals.length || !returnType.equals(func.returnType)) {
        return false;
      }
      for (int i = 0; i < formals.length; i++) {
        if (!formals[i].equals(func.formals[i])) {
          return false;
        }
        return true;
      }
    }
    return false;
  }

}
