package identifier_objects.binary_operator_functions;

import identifier_objects.PARAM;
import identifier_objects.basic_types.BOOL;
import identifier_objects.polymorhpic_types.COMPARABLE;
import symbol_table.SymbolTable;

public class GT extends BINARY_OPERATION {

  public static final String name = ">";

  public GT(BOOL returnType, COMPARABLE type, SymbolTable ST) {
    super(returnType, new PARAM(type), new PARAM(type), ST);
  }

}
