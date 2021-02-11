package middleware.ast;

import errors.WaccError;
import errors.semantic_errors.WaccSemanticError;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import java.util.List;
import org.antlr.v4.runtime.Token;
import symbol_table.SymbolTable;

public abstract class NodeAST {
  protected static SymbolTable ST = SymbolTable.TopSymbolTable();

  private static List<WaccError> semanticErrors;
  public Token token;

  public NodeAST(Token token){
    this.token = token;
  }

  public static void setSemanticErrors(List<WaccError> semanticErrors) {
    NodeAST.semanticErrors = semanticErrors;
  }

  public void check(){
    throw new Error("need to ovveride this function");
  }

  protected void addError(WaccSemanticError error) {
    semanticErrors.add(error);
  }

  protected boolean isCompatible(IDENTIFIER t1, IDENTIFIER t2) {
    if (t1 instanceof TYPE && t2 instanceof TYPE) {
      return t2.equals(t1);
    }
    return false;
  }
}
