package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.TYPE;
import java.util.List;
import middleware.arrays_ast.ArrayAST;
import middleware.expression_ast.ExpressionAST;
import middleware.function_ast.FunctionCallAST;
import middleware.pair_ast.NewPairAST;
import middleware.pair_ast.PairElemAST;
import org.antlr.v4.runtime.Token;

public class RHSAssignAST extends StatementAST {

  private ExpressionAST expr;
  private ArrayAST array;
  private NewPairAST newPair;
  private PairElemAST pairElem;
  private FunctionCallAST funcCall;

  private TYPE type;

  // RHS Assign is an expression.
  public RHSAssignAST(Token token, ExpressionAST expr) {
    super(token);
    this.expr = expr;
  }

  // RHS Assign is an array.
  public RHSAssignAST(Token token, ArrayAST array) {
    super(token);
    this.array = array;
  }

  // RHS Assign is a newpair.
  public RHSAssignAST(Token token, NewPairAST newPair) {
    super(token);
    this.newPair = newPair;
  }

  // RHS Assign is a pairElem.
  public RHSAssignAST(Token token, PairElemAST pairElem) {
    super(token);
    this.pairElem = pairElem;
  }

  // RHS Assign is a function call.
  public RHSAssignAST(Token token, FunctionCallAST funcCall) {
    super(token);
    this.funcCall = funcCall;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    if (expr != null) {
      // case when assign RHS is an expression.

      // check the expression.
      expr.check();

      // Verify that the expressions is a TYPE i.e. not a function name.
      // If it is a TYPE, then set type.
      if (!(expr.getType() instanceof TYPE)) {
        addError(new MismatchedTypes(token, expr.getType(), new TYPE()));
      } else {
        type = (TYPE) expr.getType();
      }
      return;
    }

    if (array != null) {
      // case when assign RHS is an array.

      // check the array.
      array.check();

      // Verify that the array is not null. If it is present, set the type.
      if (array.getArrayObj() == null) {
        addError(new Undefined(token, array.token.getText()));
      } else {
        type = array.getArrayObj();
      }
      return;
    }

    if (newPair != null) {
      // case when assign RHS is a newpair.

      // check the newpair.
      newPair.check();

      // verify that the newpair is not null. If it is present, set the type.
      if (newPair.getPair() == null) {
        addError(new Undefined(token, newPair.token.getText()));
      } else {
        type = newPair.getPair();
      }
      return;
    }

    if (pairElem != null) {
      // case when assign RHS is a pairElem.

      // check the pairelem.
      pairElem.check();

      // verify that the pairelem is not null. If it is present, set the type.
      if (pairElem.getType() == null) {
        addError(new Undefined(token, pairElem.token.getText()));
      } else {
        type = pairElem.getType();
      }
      return;
    }

    if (funcCall != null) {
      // case when assign RHS is a function call.

      // check the function call.
      funcCall.check();

      // verify that the function object is not null. If the function is
      // present, set type to the function's return type.
      if (funcCall.getFuncObj() == null) {
        addError(new Undefined(token, funcCall.token.getText()));
      } else {
        type = funcCall.getFuncObj().getReturnType();
      }
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return null;
  }
}

