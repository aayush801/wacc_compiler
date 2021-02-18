package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import java.util.List;
import middleware.arrays_ast.ArrayAST;
import middleware.expression_ast.ExpressionAST;
import middleware.function_ast.FunctionCallAST;
import middleware.pair_ast.NewPairAST;
import middleware.pair_ast.PairElemAST;
import org.antlr.v4.runtime.Token;

public class RHSAssignAST extends StatementAST {

  private ExpressionAST expressionAST;
  private ArrayAST arrayAST;
  private NewPairAST newPairAST;
  private PairElemAST pairElemAST;
  private FunctionCallAST functionCallAST;

  private TYPE type;

  // RHS Assign is an expression.
  public RHSAssignAST(Token token, ExpressionAST expressionAST) {
    super(token);
    this.expressionAST = expressionAST;
  }

  // RHS Assign is an array.
  public RHSAssignAST(Token token, ArrayAST arrayAST) {
    super(token);
    this.arrayAST = arrayAST;
  }

  // RHS Assign is a newpair.
  public RHSAssignAST(Token token, NewPairAST newPairAST) {
    super(token);
    this.newPairAST = newPairAST;
  }

  // RHS Assign is a pairElem.
  public RHSAssignAST(Token token, PairElemAST pairElemAST) {
    super(token);
    this.pairElemAST = pairElemAST;
  }

  // RHS Assign is a function call.
  public RHSAssignAST(Token token, FunctionCallAST functionCallAST) {
    super(token);
    this.functionCallAST = functionCallAST;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    if (expressionAST != null) {
      // case when assign RHS is an expression.

      // check the expression.
      expressionAST.check();

      if(expressionAST.getType() == null){
        // error has occurred elsewhere
        return;
      }

      // Verify that the expressions is a TYPE i.e. not a function name.
      // If it is a TYPE, then set type.
      if (!(expressionAST.getType() instanceof TYPE)) {
        addError(new MismatchedTypes(token, expressionAST.getType(), new TYPE()));
        return;
      }

      type = (TYPE) expressionAST.getType();

      return;

    }

    if (arrayAST != null) {
      // case when assign RHS is an array.

      // check the array.
      arrayAST.check();

      // Verify that the array is not null. If it is present, set the type.
      if(arrayAST.getArrayObj() == null){
        // error has occurred elsewhere
        return;
      }

      type = arrayAST.getArrayObj();

      return;

    }

    if (newPairAST != null) {
      // case when assign RHS is a newpair.

      // check the newpair.
      newPairAST.check();

      // verify that the newpair is not null. If it is present, set the type.
      if (newPairAST.getPair() == null) {
        return;
      }

      type = newPairAST.getPair();

      return;

    }

    if (pairElemAST != null) {
      // case when assign RHS is a pairElem.

      // check the pairelem.
      pairElemAST.check();

      // verify that the pairelem is not null. If it is present, set the type.
      if (pairElemAST.getType() == null) {
        return;
      }

      type = pairElemAST.getType();

      return;
    }

    if (functionCallAST != null) {
      // case when assign RHS is a function call.

      // check the function call.
      functionCallAST.check();

      // verify that the function object is not null. If the function is
      // present, set type to the function's return type.
      if (functionCallAST.getFuncObj() == null) {
        return;
      }

      type = functionCallAST.getFuncObj().getReturnType();

    }

  }
}

