package middleware.ast.expression_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.expressionNotFound;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.basic_types.*;
import org.antlr.v4.runtime.Token;

import java.util.Arrays;
import java.util.List;

public class BinOpExprAST extends ExpressionAST {

  protected final List<String> NUM_BIN_OPS
      = Arrays.asList("+", "-", "*", "/", "%");
  protected final List<String> NUM_CHAR_BIN_OPS
      = Arrays.asList(">", "<", ">=", "<=");
  protected final List<String> EXPR_BIN_OPS = Arrays.asList("==", "!=");

  private final ExpressionAST left;
  private final ExpressionAST right;
  private final String operator;

  public BinOpExprAST(Token token, ExpressionAST left, String operator,
      ExpressionAST right) {
    super(token);
    this.left = left;
    this.right = right;
    this.operator = operator;
  }

  @Override
  public void check() {
    left.check();
    right.check();
    IDENTIFIER leftType = left.getType();
    IDENTIFIER rightType = right.getType();

    if(!(leftType instanceof TYPE)){
      addError(new expressionNotFound(token,leftType));
      return ;
    }

    if(!(rightType instanceof TYPE)){
      addError(new expressionNotFound(token,rightType));
      return ;
    }

    if (NUM_BIN_OPS.contains(operator)) {
      boolean leftInt = leftType instanceof INT;
      boolean rightInt = rightType instanceof INT;
      if (!leftInt) {
        addError(new MismatchedTypes(token, leftType, new INT()));
      }
      if (!rightInt) {
        addError(new MismatchedTypes(token, rightType, new INT()));
      }
      type = new INT();
      return;
    }

    if (NUM_CHAR_BIN_OPS.contains(operator)) {
      boolean leftIntOrChar = leftType instanceof INT || leftType instanceof CHAR;
      boolean rightIntOrChar = rightType instanceof INT || rightType instanceof CHAR;
      if (!leftIntOrChar) {
        addError(new MismatchedTypes(token, leftType, new INT(), new CHAR()));
      }
      if (!rightIntOrChar) {
        addError(new MismatchedTypes(token, rightType, new INT(), new CHAR()));
      }
      if (!isCompatible(leftType, rightType)) {
        addError(new MismatchedTypes(token, rightType, leftType));
      }
      type = new BOOL();
      return;
    }

    if (EXPR_BIN_OPS.contains(operator)) {
//      boolean sameType = isCompatible(leftType, rightType);
      type = new BOOL();
      return;
    }

    boolean leftBool = leftType instanceof BOOL;
    boolean rightBool = rightType instanceof BOOL;
    if (!leftBool) {
      addError(new MismatchedTypes(token, new BOOL(), leftType));
    }
    if (!rightBool) {
      addError(new MismatchedTypes(token, new BOOL(), rightType));
    }
    type = new BOOL();
  }

}
