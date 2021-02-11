package middleware.ast.statement_ast;

import middleware.ast.arrays_ast.ArrayAST;
import middleware.ast.expression_ast.ExpressionAST;
import middleware.ast.function_ast.FunctionCallAST;
import middleware.ast.pair_ast.NewPairAST;
import middleware.ast.pair_ast.PairElemAST;
import org.antlr.v4.runtime.Token;

public class RHSAssignAST extends StatementAST {

  private ExpressionAST expr;
  private ArrayAST array;
  private NewPairAST newPair;
  private PairElemAST pairElem;
  private FunctionCallAST funcCall;

  public RHSAssignAST(Token token, ExpressionAST expr) {
    super(token);
    this.expr = expr;
  }

  public RHSAssignAST(Token token, ArrayAST array) {
    super(token);
    this.array = array;
  }

  public RHSAssignAST(Token token, NewPairAST newPair) {
    super(token);
    this.newPair = newPair;
  }

  public RHSAssignAST(Token token, PairElemAST pairElem) {
    super(token);
    this.pairElem = pairElem;
  }

  public RHSAssignAST(Token token, FunctionCallAST funcCall) {
    super(token);
    this.funcCall = funcCall;
  }

  @Override
  public void check() {
    if (expr != null) {
      expr.check();
      return;
    }
    if (array != null) {
      array.check();
      return;
    }
    if (newPair != null) {
      newPair.check();
      return;
    }
    if (pairElem != null) {
      pairElem.check();
      return;
    }
    if (funcCall != null) {
      funcCall.check();
    }
  }
}

