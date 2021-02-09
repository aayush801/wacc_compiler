package identifier_objects.unary_operator_functions;

import identifier_objects.PARAM;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
import symbol_table.SymbolTable;

public class ORD extends UNARY_OPERATION {

  public static final String name = "ord";

  public ORD(INT returnType, CHAR paramType, SymbolTable ST) {
    super(returnType, new PARAM(paramType), ST);
  }
}
