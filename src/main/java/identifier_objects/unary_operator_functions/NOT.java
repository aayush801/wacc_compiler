package identifier_objects.unary_operator_functions;

import identifier_objects.PARAM;
import identifier_objects.basic_types.BOOL;
import symbol_table.SymbolTable;

public class NOT extends UNARY_OPERATION {

  public static final String name = "not";

  public NOT(BOOL type, SymbolTable ST) {
    super(type, new PARAM(type), ST);
  }
}
