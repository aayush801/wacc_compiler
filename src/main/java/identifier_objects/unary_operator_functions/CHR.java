package identifier_objects.unary_operator_functions;

import identifier_objects.PARAM;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
import symbol_table.SymbolTable;

public class CHR extends UNARY_OPERATION {

  public static final String name = "chr";

  public CHR(CHAR returnType, INT paramType, SymbolTable ST) {
    super(returnType, new PARAM(paramType), ST);
  }
}
