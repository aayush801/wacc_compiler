package wacc.middleware.symbol_table;

import wacc.backend.registers.StackPointer;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.VARIABLE;
import wacc.frontend.identifier_objects.basic_types.BOOL;
import wacc.frontend.identifier_objects.basic_types.CHAR;
import wacc.frontend.identifier_objects.basic_types.INT;
import wacc.frontend.identifier_objects.basic_types.PAIR;
import wacc.frontend.identifier_objects.basic_types.STR;
import wacc.frontend.identifier_objects.basic_types.VOID;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SymbolTable {

  private final SymbolTable encSymTable;
  protected final LinkedHashMap<String, IDENTIFIER> dict;
  protected TYPE scopeReturnType = null;
  int stackPtr = 0, freePtr = 0;
  public static int funcIndex = 0;
  public static Map<String, List<Integer>> funcIndices = new HashMap<>();

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
    dict = new LinkedHashMap<>();
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
    VOID voidType = new VOID();

    // add literals to symbol table
    st.add("int", intType);
    st.add("string", strType);
    st.add("char", charType);
    st.add("pair", pairType);
    st.add("bool", boolType);
    st.add("void", voidType);

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

  // total estimated size of local variables
  public int calculateScopeSize() {
    return getVariables().stream().mapToInt(e -> e.getType().getSize()).sum();
  }

  // gets a list of all the variables defined within the scope
  public List<VARIABLE> getVariables() {
    return dict.values().stream().filter(e -> e instanceof VARIABLE)
        .map(e -> (VARIABLE) e)
        .collect(Collectors.toList());
  }

  public void saveStackState(StackPointer SP) {
    stackPtr = SP.getStackPtr();
    freePtr = SP.getFreePtr();
  }

  public boolean inScope(SymbolTable encScope){
    return this == encScope || getEncSymTable() != null && getEncSymTable().inScope(encScope);
  }

  public void restoreStackState(StackPointer SP) {
    SP.setState(stackPtr, freePtr);
  }

  public static void reset(){
    funcIndex = 0;
    funcIndices = new HashMap<>();
  }

}
