package identifier_objects.binary_operator_functions;

import identifier_objects.PARAM;
import identifier_objects.basic_types.BOOL;
import identifier_objects.polymorhpic_types.EXPR;
import symbol_table.SymbolTable;

public class EQ extends BINARY_OPERATION {

  public static final String name = "==";

  public EQ(BOOL returnType, EXPR type, SymbolTable ST) {
    super(returnType, new PARAM(type), new PARAM(type), ST);
  }

}
