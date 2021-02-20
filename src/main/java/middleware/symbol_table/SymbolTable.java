package middleware.symbol_table;

import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import frontend.identifier_objects.basic_types.PAIR;
import frontend.identifier_objects.basic_types.STR;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SymbolTable {

  private final SymbolTable encSymTable;
  private final Map<String, IDENTIFIER> dict;
  protected TYPE scopeReturnType = null;
  public int index = 0;

  public SymbolTable() {
    this(null);
  }

  public SymbolTable(SymbolTable st, TYPE scopeReturnType) {
    this(st);
    this.scopeReturnType = scopeReturnType;
  }

  public SymbolTable(SymbolTable st) {
    encSymTable = st;
    if (st != null) {
      scopeReturnType = st.getScopeReturnType();
    }
    dict = new HashMap<>();
  }

  // generate top symbol table
  static public SymbolTable TopSymbolTable() {
    SymbolTable st = new SymbolTable();

    // literal types are only created once, as they cannot be redefined in the program
    INT intType = new INT(Integer.MIN_VALUE, Integer.MAX_VALUE);
    STR strType = new STR();
    CHAR charType = new CHAR(0, 255);
    PAIR pairType = new PAIR();
    BOOL boolType = new BOOL();

    // add literals to symbol table
    st.add(INT.name, intType);
    st.add(STR.name, strType);
    st.add(CHAR.name, charType);
    st.add(PAIR.name, pairType);
    st.add(BOOL.name, boolType);

    return st;
  }

  public SymbolTable getEncSymTable() {
    return encSymTable;
  }

  public TYPE getScopeReturnType() {
    return scopeReturnType;
  }


  public void add(String name, IDENTIFIER obj) {
    dict.put(name, obj);
  }

  // lookup identifier in the local scope
  public IDENTIFIER lookup(String name) {
    return dict.get(name);
  }

  // backtracking algorithm to lookup identifier if it is in scope
  public IDENTIFIER lookupAll(String name) {
    IDENTIFIER obj = null;
    SymbolTable st;
    for (st = this; obj == null && st != null; st = st.encSymTable) {
      obj = st.lookup(name);
    }
    return obj;
  }


  public int sizeOfVariablesDeclaredInScope() {
    AtomicInteger count = new AtomicInteger();
    dict.values().forEach(v -> {
      if (v instanceof VARIABLE) {
        TYPE varType = ((VARIABLE) v).getType();
        count.addAndGet(varType.getSize());
      }
    });
    return count.get();
  }

}
