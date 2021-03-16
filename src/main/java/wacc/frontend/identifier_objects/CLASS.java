package wacc.frontend.identifier_objects;

import wacc.middleware.symbol_table.SymbolTable;

public class CLASS extends TYPE {

  private final SymbolTable scopeST;

  public CLASS(String name, SymbolTable scopeST) {
    super(name);
    this.scopeST = scopeST;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof CLASS && name.equals(((CLASS) o).getName());
  }

  @Override
  public int getSize() {
    return 4;
  }
}
