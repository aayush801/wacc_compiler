package middleware.ast.statement_ast;

import middleware.ast.arrays_ast.ArrayElemAST;
import middleware.ast.pair_ast.PairElemAST;
import org.antlr.v4.runtime.Token;

public class LHSAssignAST extends StatementAST {

  private String ident;
  private ArrayElemAST arrayElemAST;
  private PairElemAST pairElemAST;

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
    super.check();
  }
}
