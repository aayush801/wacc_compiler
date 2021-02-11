package middleware.ast.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.PARAM;
import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.arrays_ast.ArrayElemAST;
import middleware.ast.pair_ast.PairElemAST;
import org.antlr.v4.runtime.Token;

public class LHSAssignAST extends StatementAST {

  private String ident;
  private ArrayElemAST arrayElemAST;
  private PairElemAST pairElemAST;

  private TYPE type;

  public TYPE getType() {
    return type;
  }

  public LHSAssignAST(Token token, String ident) {
    super(token);
    this.ident = ident;
  }

  public LHSAssignAST(Token token, ArrayElemAST arrayElemAST) {
    super(token);
    this.arrayElemAST = arrayElemAST;
  }

  public LHSAssignAST(Token token, PairElemAST pairElemAST) {
    super(token);
    this.pairElemAST = pairElemAST;
  }

  //Ident, base arrayElem, pairElem.
  @Override
  public void check() {
    if (ident != null) {
      IDENTIFIER obj = ST.lookupAll(ident);

      if (obj == null) {
        addError(new Undefined(token, ident));
        return;
      }

      if(obj instanceof VARIABLE){
        type = ((VARIABLE) obj).getType();
        return;
      }

      if(obj instanceof PARAM){
        type = ((PARAM) obj).getType();
        return;
      }

      if (!(obj instanceof TYPE)) {
        addError(new MismatchedTypes(token, obj, new EXPR()));
        return;
      }

      type = (TYPE) obj;

      return;
    }

    if (arrayElemAST != null) {
      arrayElemAST.check();
      if (arrayElemAST.getType() == null) {
        addError(new Undefined(token, arrayElemAST.token.getText()));
      } else {
        type = arrayElemAST.getType();
      }
      return;
    }

    if (pairElemAST != null) {
      pairElemAST.check();
      if (pairElemAST.getType() == null) {
        addError(new Undefined(token, pairElemAST.token.getText()));
      } else {
        type = pairElemAST.getType();
      }
    }
  }
}