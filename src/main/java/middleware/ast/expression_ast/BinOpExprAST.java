package middleware.ast.expression_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.NotAFunction;
import errors.semantic_errors.expressionNotFound;
import identifier_objects.IDENTIFIER;
import identifier_objects.basic_types.BOOL;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
import identifier_objects.TYPE;

import org.antlr.v4.runtime.Token;

public class BinOpExprAST extends ExpressionAST {

  private final ExpressionAST leftExprAST;
  private final ExpressionAST rightExprAST;
  private final String operator;

  public BinOpExprAST(Token token, ExpressionAST leftExprAST, String operator,
      ExpressionAST rightExprAST) {
    super(token);
    this.leftExprAST = leftExprAST;
    this.rightExprAST = rightExprAST;
    this.operator = operator;
  }

  /* ================== OPERATION PARAMETER TYPING CHECKS ================== */
  /* ==================== PS: these are helper functions =================== */

  private void checkArithmeticParams(IDENTIFIER leftType, IDENTIFIER rightType) {
    boolean leftIsInt = leftType instanceof INT;
    boolean rightIsInt = rightType instanceof INT;

    if (!leftIsInt) {

      addError(new MismatchedTypes(leftExprAST.token, leftType, new INT()));

    }

    if (!rightIsInt) {

      addError(new MismatchedTypes(rightExprAST.token, rightType, new INT()));

    }

    type = new INT();
  }

  private void checkComparableParams(IDENTIFIER leftType, IDENTIFIER rightType) {
    boolean leftIntOrChar = leftType instanceof INT || leftType instanceof CHAR;
    boolean rightIntOrChar = rightType instanceof INT || rightType instanceof CHAR;

    if (!leftIntOrChar) {

      addError(new MismatchedTypes(leftExprAST.token, leftType, new INT(), new CHAR()));

    }

    if (!rightIntOrChar) {

      addError(new MismatchedTypes(rightExprAST.token, rightType, new INT(), new CHAR()));

    }

    if (leftIntOrChar && rightIntOrChar && !isCompatible(leftType, rightType)) {

      addError(new MismatchedTypes(token, leftType, rightType));

    }

    type = new BOOL();
  }

  private void checkBoolLogicParams(IDENTIFIER leftType, IDENTIFIER rightType){
    boolean leftIsBool = leftType instanceof BOOL;
    boolean rightIsBool = rightType instanceof BOOL;

    if (!leftIsBool) {

      addError(new MismatchedTypes(leftExprAST.token, new BOOL(), leftType));

    }

    if (!rightIsBool) {

      addError(new MismatchedTypes(rightExprAST.token, new BOOL(), rightType));

    }

    if (leftIsBool && rightIsBool && !isCompatible(leftType, rightType)) {

      addError(new MismatchedTypes(token, rightType, leftType));

    }

    type = new BOOL();
  }

  private void checkEquatableParams(IDENTIFIER leftType, IDENTIFIER rightType) {

    if (!isCompatible(leftType, rightType)) {

      addError(new MismatchedTypes(token, rightType, leftType));

    }

    type = new BOOL();
  }

  /* ========================================================================= */


    @Override
  public void check() {
      leftExprAST.check();
      rightExprAST.check();
      IDENTIFIER leftType = leftExprAST.getType();
      IDENTIFIER rightType = rightExprAST.getType();

      boolean leftIsType = leftType instanceof TYPE;
      boolean rightIsType = rightType instanceof TYPE;

      if (!leftIsType) {

        addError(new expressionNotFound(leftExprAST.token, leftType));

      }

      if (!rightIsType) {

        addError(new expressionNotFound(rightExprAST.token, rightType));

      }

      switch (operator) {
        // ARITHMETIC Operators
        case "+":
        case "-":
        case "*":
        case "%":
        case "/":
          checkArithmeticParams(leftType, rightType);
          break;
        // EQUATABLE Operators
        case "==":
        case "!=":
          checkEquatableParams(leftType, rightType);
          break;
        // COMPARABLE Operators
        case ">":
        case "<":
        case ">=":
        case "<=":
          checkComparableParams(leftType, rightType);
          break;
        // BOOLEAN Operators
        case "&&":
        case "||":
          checkBoolLogicParams(leftType, rightType);
          break;
        // Unrecognized Operator
        default:
          addError(new NotAFunction(token));
          break;
      }



    }

}
