package identifier_objects.unary_operator_functions;

import identifier_objects.PARAM;
import identifier_objects.polymorhpic_types.EXPR;
import symbol_table.SymbolTable;

public class PRINT_LINE extends UNARY_OPERATION {

  public static final String name = "println";

  public PRINT_LINE(EXPR type, SymbolTable st) {
    super(null, new PARAM(type), st);
  }
}
