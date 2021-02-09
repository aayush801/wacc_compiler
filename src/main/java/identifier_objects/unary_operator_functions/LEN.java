package identifier_objects.unary_operator_functions;

import identifier_objects.PARAM;
import identifier_objects.basic_types.ARRAY;
import identifier_objects.basic_types.INT;
import symbol_table.SymbolTable;

public class LEN extends UNARY_OPERATION {

  public static final String name = "len";

  public LEN(INT returnType, ARRAY paramType, SymbolTable ST) {
    super(returnType, new PARAM(paramType), ST);
  }
}
