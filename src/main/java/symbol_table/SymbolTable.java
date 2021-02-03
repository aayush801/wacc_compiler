package symbol_table;

import java.util.Map;
import java.util.HashMap;
import symbol_table.identifier_objects.Identifier;

public class SymbolTable {

  private SymbolTable encSymTable;
  private Map<String, Identifier> dict;

  public SymbolTable(SymbolTable st) {
    encSymTable = st;
    dict = new HashMap<>();
  }

  public SymbolTable() {
    this(null);
  }

  public Map add(String name, Identifier obj) {
    dict.put(name, obj);
    return dict;
  }

  public Identifier lookup(String name) {
    return dict.get(name);
  }

  public Identifier lookupAll(String name) {
    SymbolTable st = this;
    Identifier obj;
    while (st != null) {
      obj = st.lookup(name);
      if (obj != null) {
        return obj;
      }
      st = st.encSymTable;
    }
    return null;
  }

}
