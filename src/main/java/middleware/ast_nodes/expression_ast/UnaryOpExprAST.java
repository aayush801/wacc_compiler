package middleware.ast_nodes.expression_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.NotAFunction;
import errors.semantic_errors.expressionNotFound;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.ARRAY;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import middleware.ExpressionAST;
import middleware.NodeASTVisitor;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class UnaryOpExprAST extends ExpressionAST {

  private final ExpressionAST expr;
  private final String operator;
  private SymbolTable scopeST;

  public UnaryOpExprAST(ParserRuleContext ctx, ExpressionAST expr, String operator) {
    super(ctx);
    this.expr = expr;
    this.operator = operator;
  }

  public ExpressionAST getExpr() {
    return expr;
  }

  public String getOperator() {
    return operator;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }

  /* ================== OPERATION PARAMETER TYPING CHECKS ================== */

  private void checkNotParam(IDENTIFIER exprType) {

    if (!(exprType instanceof BOOL)) {

      addError(new MismatchedTypes(ctx, exprType, new BOOL()));

    }

    type = new BOOL();
  }

  private void checkNegateParam(IDENTIFIER exprType) {

    if (!(exprType instanceof INT)) {

      addError(new MismatchedTypes(ctx, exprType, new INT()));

    }

    type = new INT();
  }

  private void checkLengthParam(IDENTIFIER exprType) {

    if (!(exprType instanceof ARRAY)) {

      addError(new MismatchedTypes(ctx, exprType, new ARRAY(new TYPE())));

    }

    type = new INT();
  }

  private void checkChrParam(IDENTIFIER exprType) {

    if (!(exprType instanceof INT)) {

      addError(new MismatchedTypes(ctx, exprType, new INT()));

    }

    type = new CHAR();
  }

  private void checkOrdParam(IDENTIFIER exprType) {

    if (!(exprType instanceof CHAR)) {

      addError(new MismatchedTypes(ctx, exprType, new CHAR()));

    }

    type = new INT();
  }

  /* ========================================================================= */


  @Override
  public void check() {

    scopeST = ST;
    expr.check();
    IDENTIFIER exprType = expr.getType();

    if (exprType == null) {
      // error occurred elsewhere
      return;
    }

    if (!(exprType instanceof TYPE)) {
      addError(new expressionNotFound(ctx, exprType));
      return;
    }

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
        addError(new NotAFunction(ctx));
        break;
    }
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}


