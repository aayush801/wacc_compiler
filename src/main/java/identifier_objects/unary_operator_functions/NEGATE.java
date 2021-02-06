package identifier_objects.unary_operator_functions;

import identifier_objects.PARAM;
import identifier_objects.basic_types.INT;
import symbol_table.SymbolTable;

public class NEGATE extends UNARY_OPERATION {
  public static final String name = "negate";

  public NEGATE(INT type, SymbolTable ST){
    super(type, new PARAM(type), ST);
  }
}
