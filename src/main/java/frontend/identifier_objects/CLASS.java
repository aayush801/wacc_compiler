package frontend.identifier_objects;

import java.util.ArrayList;
import java.util.List;
import middleware.symbol_table.SymbolTable;

public class CLASS extends TYPE {

  private SymbolTable ST;
  private final String superclass;

  public CLASS(String superclass) {
    super("class");
    this.superclass = superclass;
  }

  public CLASS() {
    this(null);
  }

  public SymbolTable getST() {
    return ST;
  }

  public void setST(SymbolTable ST) {
    this.ST = ST;
  }

}
