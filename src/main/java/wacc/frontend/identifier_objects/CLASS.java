package wacc.frontend.identifier_objects;

import wacc.middleware.SymbolTable;

public class CLASS extends TYPE {

  private SymbolTable ST;

  public CLASS(String name, SymbolTable symbolTable) {
    super(name);
    setST(symbolTable);
  }

  public SymbolTable getST() {
    return ST;
  }

  public void setST(SymbolTable ST) {
    this.ST = ST;
  }

}
