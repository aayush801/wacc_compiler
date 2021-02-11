package identifier_objects.binary_operator_functions;

import identifier_objects.FUNCTION;
import identifier_objects.PARAM;
import identifier_objects.TYPE;
import java.util.Arrays;
import symbol_table.SymbolTable;

public class BINARY_OPERATION extends FUNCTION {

  public BINARY_OPERATION(TYPE returnType, PARAM left, PARAM right, SymbolTable st) {
    super(returnType);
    formals.add(left);
    formals.add(right);
    setST(st);
  }

}
