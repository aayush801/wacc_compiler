package symbol_table.identifier_objects;

import symbol_table.SymbolTable;

public class Function extends Identifier {

  protected final Type returnType;
  protected final SymbolTable st;
  protected final Param[] formals;

  public Function(Type returnType, Param[] formals, SymbolTable st) {
    super("function");
    this.returnType = returnType;
    this.st = st;
    this.formals = formals;
  }
}
