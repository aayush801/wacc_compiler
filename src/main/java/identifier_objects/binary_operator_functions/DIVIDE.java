package identifier_objects.binary_operator_functions;

import identifier_objects.PARAM;
import identifier_objects.basic_types.INT;
import symbol_table.SymbolTable;

public class DIVIDE extends BINARY_OPERATION {

  public static final String name = "/";

  public DIVIDE(INT type, SymbolTable ST) {
    super(type, new PARAM(type), new PARAM(type), ST);
  }

}
