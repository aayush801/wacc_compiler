package identifier_objects;

import java.util.List;
import symbol_table.SymbolTable;

public class FUNCTION extends IDENTIFIER {

  public final TYPE returnType;
  public final List<PARAM> formals;
  public SymbolTable ST;

  public FUNCTION(TYPE returnType, List<PARAM> formals) {
    super("function");
    this.returnType = returnType;
    this.formals = formals;
  }

  public SymbolTable getST() {
    return ST;
  }

  public void setST(SymbolTable ST) {
    this.ST = ST;
  }

  public TYPE getReturnType() {
    return returnType;
  }

  public boolean equals(Object o) {
    if (o instanceof FUNCTION) {
      FUNCTION func = (FUNCTION) o;
      if (func.formals.size() == formals.size() && returnType.equals(func.returnType)) {
        for (int i = 0; i < formals.size(); i++) {
          if (!formals.get(i).equals(func.formals.get(i))) {
            return false;
          }
        }
        return true;
      }
    }
    return false;
  }
}
