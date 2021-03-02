package middleware.expression_ast;

import backend.NodeASTVisitor;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.NotAFunction;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import middleware.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class BinOpExprAST extends ExpressionAST {

  private final ExpressionAST leftExprAST, rightExprAST;
  private final String operator;

  public BinOpExprAST(ParserRuleContext ctx, ExpressionAST leftExprAST,
      String operator, ExpressionAST rightExprAST) {
    super(ctx);
    this.leftExprAST = leftExprAST;
    this.rightExprAST = rightExprAST;
    this.operator = operator;
  }

  public ExpressionAST getLeftExprAST() {
    return leftExprAST;
  }

  public ExpressionAST getRightExprAST() {
    return rightExprAST;
  }

  public String getOperator() {
    return operator;
  }
  /* ================== OPERATION PARAMETER TYPING CHECKS ================== */
  /* ==================== PS: these are helper functions =================== */


  // Check that leftType and rightType are INTs.
  private void checkArithmeticParams(IDENTIFIER leftType, IDENTIFIER rightType) {
    boolean leftIsInt = leftType instanceof INT;
    boolean rightIsInt = rightType instanceof INT;

    if (!leftIsInt) {

      addError(new MismatchedTypes(leftExprAST.ctx, leftType, new INT()));

    }

    if (!rightIsInt) {

      addError(new MismatchedTypes(rightExprAST.ctx, rightType, new INT()));

    }

    type = new INT();
  }

  // Check that leftType and rightType are both either INTs or CHARS.
  // If this holds, then check that the types match.
  private void checkComparableParams(IDENTIFIER leftType, IDENTIFIER rightType) {

    boolean leftIntOrChar = leftType instanceof INT || leftType instanceof CHAR;
    boolean rightIntOrChar = rightType instanceof INT || rightType instanceof CHAR;

    boolean error = false;

    if (!leftIntOrChar) {

      addError(new MismatchedTypes(leftExprAST.ctx, leftType, new INT(), new CHAR()));
      error = true;

    }

    if (!rightIntOrChar) {

      addError(new MismatchedTypes(rightExprAST.ctx, rightType, new INT(), new CHAR()));
      error = true;

    }

    if (!error) {

      if (!isCompatible(leftType, rightType)) {

        addError(new MismatchedTypes(ctx, rightType, leftType));

      }

    }

    type = new BOOL();
  }

  // Check that leftType and rightType are BOOLs.
  private void checkBoolLogicParams(IDENTIFIER leftType, IDENTIFIER rightType) {
    boolean leftIsBool = leftType instanceof BOOL;
    boolean rightIsBool = rightType instanceof BOOL;

    if (!leftIsBool) {

      addError(new MismatchedTypes(leftExprAST.ctx, new BOOL(), leftType));

    }

    if (!rightIsBool) {

      addError(new MismatchedTypes(rightExprAST.ctx, new BOOL(), rightType));

    }

    type = new BOOL();
  }

  // Check that lef type and right type are type compatible.
  private void checkEquatableParams(IDENTIFIER leftType, IDENTIFIER rightType) {

    if (!isCompatible(leftType, rightType)) {

      addError(new MismatchedTypes(ctx, rightType, leftType));

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

    if (leftType == null || rightType == null) {
      // error has occurred elsewhere
      return;
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
        addError(new NotAFunction(ctx));
        break;
    }
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
