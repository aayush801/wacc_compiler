package identifier_objects.unary_operator_functions;

import identifier_objects.PARAM;
import identifier_objects.polymorhpic_types.EXPR;
import symbol_table.SymbolTable;

public class READ extends UNARY_OPERATION {

  public static final String name = "read";

  public READ(EXPR type, SymbolTable st) {
    super(null, new PARAM(type), st);
  }
}
