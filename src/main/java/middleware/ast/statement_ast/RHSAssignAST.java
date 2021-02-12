package middleware.ast.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.TYPE;
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

  private TYPE type;

  public TYPE getType() {
    return type;
  }

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
      if (!(expr.getType() instanceof TYPE)) {
        addError(new MismatchedTypes(token, expr.getType(), new TYPE()));
      } else {
        type = (TYPE) expr.getType();
      }
      return;
    }

    if (array != null) {
      array.check();
      if (array.getArrayObj() == null) {
        addError(new Undefined(token, array.token.getText()));
      } else {
        type = array.getArrayObj();
      }
      return;
    }

    if (newPair != null) {
      newPair.check();
      if (newPair.getPair() == null) {
        addError(new Undefined(token, newPair.token.getText()));
      } else {
        type = newPair.getPair();
      }
      return;
    }

    if (pairElem != null) {
      pairElem.check();
      if (pairElem.getType() == null) {
        addError(new Undefined(token, pairElem.token.getText()));
      } else {
        type = pairElem.getType();
      }
      return;
    }

    if (funcCall != null) {
      funcCall.check();
      if (funcCall.getFuncObj() == null) {
        addError(new Undefined(token, funcCall.token.getText()));
      } else {
        type = funcCall.getFuncObj().getReturnType();
      }
    }
  }
}

