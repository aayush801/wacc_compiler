package wacc.frontend.identifier_objects;

import wacc.middleware.SymbolTable;

public class CLASS extends TYPE {

  private final SymbolTable scopeST;

  public CLASS(String name, SymbolTable scopeST) {
    super(name);
    this.scopeST = scopeST;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof CLASS && name.equals(((CLASS) o).getName());
  }
}
