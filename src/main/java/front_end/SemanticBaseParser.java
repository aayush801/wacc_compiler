package front_end;

import antlr.WaccParserBaseVisitor;
import identifier_objects.FUNCTION;
import identifier_objects.IDENTIFIER;
import identifier_objects.PARAM;
import identifier_objects.TYPE;
import org.antlr.v4.runtime.tree.ParseTree;
import symbol_table.SymbolTable;

public abstract class SemanticBaseParser extends WaccParserBaseVisitor<IDENTIFIER> {

  protected SymbolTable ST;

  // initialize with the top symbol table
  public SemanticBaseParser() {
    this.ST = SymbolTable.TopSymbolTable();
  }

  protected boolean isCompatible(TYPE t1, TYPE t2) {
    return t1.equals(t2);
  }

  protected TYPE visitFunctionCall(String funcIdentifier, ParseTree[] params) {
    // lookup the operator function
    IDENTIFIER function = ST.lookupAll(funcIdentifier);
    if (function == null) {
      System.out.println("Error" + funcIdentifier + " undefined");
      return null;
    } else if (!(function instanceof FUNCTION)) {
      System.out.println(funcIdentifier + " is not a function");
      return null;
    } else if(params.length != ((FUNCTION) function).formals.length) {
      System.out.println("Error : "+ funcIdentifier + ", invalid number of parameters given");
      return null;
    }else{
      // checks all the parameter types match up
      for (int i = 0; i < params.length; i++) {
        IDENTIFIER actual = visit(params[i]);
        if (actual == null) {
          System.out.println(
              "ERROR: " + params[i].getText() + " is undefined");
          return null;
        } else if (!(actual instanceof TYPE)) {
          System.out.println(
              "ERROR: expression : " + params[i].getText() + " has no type");
          return null;
        } else {
          PARAM formal = ((FUNCTION) function).formals[i];
          if (!isCompatible((TYPE) actual, formal.type)) {
            System.out.println(
                "ERROR: incompatible types - '" + params[i].getParent().getText() + "' EXPECTED : "
                    + formal.type + ", ACTUAL : "
                    + actual);
            return null;
          }
        }
      }
      return ((FUNCTION) function).getReturnType();
    }
  }
}
