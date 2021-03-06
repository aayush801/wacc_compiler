package wacc.frontend.identifier_objects;

import java.util.ArrayList;
import java.util.List;
import wacc.middleware.symbol_table.SymbolTable;

public class FUNCTION extends IDENTIFIER {

  public final TYPE returnType;
  public List<PARAM> formals = new ArrayList<>();
  private SymbolTable ST;

  private String name = "function";

  public FUNCTION(TYPE returnType) {
    super("function");
    this.returnType = returnType;
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

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  public boolean equals(Object o) {
    if (o instanceof FUNCTION) {
      FUNCTION func = (FUNCTION) o;
      if (func.formals.size() == formals.size()
          && returnType.equals(func.returnType)) {
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
