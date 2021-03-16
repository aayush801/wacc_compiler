package wacc.middleware.symbol_table;

import java.util.List;
import java.util.stream.Collectors;
import wacc.frontend.identifier_objects.FIELD;
import wacc.frontend.identifier_objects.VARIABLE;
import wacc.middleware.symbol_table.SymbolTable;

public class ClassSymbolTable extends SymbolTable {

  String className;

  public ClassSymbolTable(SymbolTable st, String className) {
    super(st);
    this.className = className;
  }

  // total estimated size of local variables
  public int calculateFieldSize() {
    return getFields().stream().mapToInt(e -> e.getType().getSize()).sum();
  }

  // gets a list of all the variables defined within the scope
  public List<FIELD> getFields() {
    return dict.values().stream().filter(e -> e instanceof FIELD)
        .map(e -> (FIELD) e)
        .collect(Collectors.toList());
  }

  public String getClassName() {
    return className;
  }
}
