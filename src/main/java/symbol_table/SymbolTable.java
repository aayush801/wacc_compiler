package symbol_table;

import identifier_objects.IDENTIFIER;
import identifier_objects.basic_types.ARRAY;
import identifier_objects.basic_types.BOOL;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
import identifier_objects.basic_types.PAIR;
import identifier_objects.basic_types.STR;
import identifier_objects.binary_operator_functions.AND;
import identifier_objects.binary_operator_functions.DIVIDE;
import identifier_objects.binary_operator_functions.MINUS;
import identifier_objects.binary_operator_functions.MOD;
import identifier_objects.binary_operator_functions.MULTIPLY;
import identifier_objects.binary_operator_functions.OR;
import identifier_objects.binary_operator_functions.PLUS;
import identifier_objects.intermediate_types.EXPR;
import identifier_objects.unary_operator_functions.CHR;
import identifier_objects.unary_operator_functions.LEN;
import identifier_objects.unary_operator_functions.NEGATE;
import identifier_objects.unary_operator_functions.NOT;
import identifier_objects.unary_operator_functions.ORD;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

  private final SymbolTable encSymTable;
  private final Map<String, IDENTIFIER> dict;

  // generate top symbol table
  static public SymbolTable TopSymbolTable() {
    SymbolTable st = new SymbolTable();

    // define types
    INT intType = new INT(Integer.MIN_VALUE, Integer.MAX_VALUE);
    STR strType = new STR();
    CHAR charType = new CHAR(0, 255);
    PAIR pairType = new PAIR();
    BOOL boolType = new BOOL();
    ARRAY arrayType = new ARRAY(new EXPR(), 0);

    // add literals to symbol table
    st.add(INT.name, intType);
    st.add(STR.name, strType);
    st.add(CHAR.name, charType);
    st.add(PAIR.name, pairType);
    st.add(BOOL.name, boolType);
    st.add(ARRAY.name, arrayType);

    // add primitive binary functions
    st.add(PLUS.name, new PLUS(intType, st));
    st.add(MINUS.name, new MINUS(intType, st));
    st.add(MULTIPLY.name, new MULTIPLY(intType, st));
    st.add(DIVIDE.name, new DIVIDE(intType, st));
    st.add(MOD.name, new MOD(intType, st));
    st.add(AND.name, new AND(boolType, st));
    st.add(OR.name, new OR(boolType, st));

    // add primitive unary functions
    st.add(CHR.name, new CHR(charType, intType, st));
    st.add(ORD.name, new ORD(intType, charType, st));
    st.add(LEN.name, new LEN(intType, arrayType, st));
    st.add(NEGATE.name, new NEGATE(intType, st));
    st.add(NOT.name, new NOT(boolType, st));

    return st;
  }

  public SymbolTable() {
    this(null);
  }

  public SymbolTable(SymbolTable st) {
    encSymTable = st;
    dict = new HashMap<>();
  }

  public void add(String name, IDENTIFIER obj) {
    dict.put(name, obj);
  }

  // lookup identifier in the current symbol table
  public IDENTIFIER lookup(String name) {
    return dict.get(name);
  }

  // backtracking algorithm to lookup identifier if it is in scope
  public IDENTIFIER lookupAll(String name) {
    SymbolTable st = this;
    IDENTIFIER obj;
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
