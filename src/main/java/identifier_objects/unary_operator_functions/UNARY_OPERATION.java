package identifier_objects.unary_operator_functions;

import identifier_objects.FUNCTION;
import identifier_objects.PARAM;
import identifier_objects.TYPE;
import symbol_table.SymbolTable;

public class UNARY_OPERATION extends FUNCTION {

  public UNARY_OPERATION(TYPE returnType, PARAM param, SymbolTable st) {
    super(returnType, new PARAM[]{param}, st);
  }
}
