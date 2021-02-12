package middleware.ast.expression_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.NotAFunction;
import identifier_objects.IDENTIFIER;
import identifier_objects.basic_types.*;
import identifier_objects.polymorhpic_types.EXPR;
import org.antlr.v4.runtime.Token;

public class UnaryOpExprAST extends ExpressionAST {

  private final ExpressionAST expr;
  private final String operator;

  public UnaryOpExprAST(Token token, ExpressionAST expr, String operator) {
    super(token);
    this.expr = expr;
    this.operator = operator;
  }

  /* ================== OPERATION PARAMETER TYPING CHECKS ================== */

  private void checkNotParam(IDENTIFIER exprType) {

    if (!(exprType instanceof BOOL)) {

      addError(new MismatchedTypes(token, exprType, new BOOL()));

    }

    type = new BOOL();
  }

  private void checkNegateParam(IDENTIFIER exprType) {

    if (!(exprType instanceof INT)) {

      addError(new MismatchedTypes(token, exprType, new INT()));

    }

    type = new INT();
  }

  private void checkLengthParam(IDENTIFIER exprType) {

    if (!(exprType instanceof ARRAY)) {

      addError(new MismatchedTypes(token, exprType, new ARRAY(new EXPR())));

    }

    type = new INT();
  }

  private void checkChrParam(IDENTIFIER exprType) {

    if (!(exprType instanceof INT)) {

      addError(new MismatchedTypes(token, exprType, new INT()));

    }

    type = new CHAR();
  }

  private void checkOrdParam(IDENTIFIER exprType) {

    if (!(exprType instanceof CHAR)) {

      addError(new MismatchedTypes(token, exprType, new CHAR()));

    }

    type = new INT();
  }

  /* ========================================================================= */


  @Override
  public void check() {

    expr.check();
    IDENTIFIER exprType = expr.getType();

    switch (operator) {
      // NOT Operator
      case "!":
        checkNotParam(exprType);
        break;
      // NEGATE Operator
      case "-":
        checkNegateParam(exprType);
        break;
      // LENGTH Operator
      case "len":
        checkLengthParam(exprType);
        break;
      // CHR Operator
      case "chr":
        checkChrParam(exprType);
        break;
      // ORD Operator
      case "ord":
        checkOrdParam(exprType);
        break;
      // Unrecognized Operator
      default:
        addError(new NotAFunction(token));
        break;
    }

  }

}


