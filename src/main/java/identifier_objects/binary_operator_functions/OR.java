package identifier_objects.binary_operator_functions;

import identifier_objects.PARAM;
import identifier_objects.basic_types.BOOL;
import symbol_table.SymbolTable;

public class OR extends BINARY_OPERATION {

  public static final String name = "||";

  public OR(BOOL type, SymbolTable ST) {
    super(type, new PARAM(type), new PARAM(type), ST);
  }

}
