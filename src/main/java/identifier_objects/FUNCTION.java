package identifier_objects;

import symbol_table.SymbolTable;

public class FUNCTION extends TYPE {

  public final TYPE returnType;
  public final SymbolTable ST;
  public final PARAM[] formals;

  public FUNCTION(TYPE returnType, PARAM[] formals, SymbolTable ST) {
    this.returnType = returnType;
    this.ST = new SymbolTable(ST);
    this.formals = formals;
  }

  @Override
  public TYPE getType() {
    return this.returnType;
  }


}
