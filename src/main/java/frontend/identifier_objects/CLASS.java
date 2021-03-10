package frontend.identifier_objects;

import middleware.symbol_table.SymbolTable;

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
