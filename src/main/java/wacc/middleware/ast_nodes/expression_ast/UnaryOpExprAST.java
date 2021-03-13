package wacc.middleware.ast_nodes.expression_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.errors.semantic_errors.NotAFunction;
import wacc.frontend.identifier_objects.POINTER;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.basic_types.ARRAY;
import wacc.frontend.identifier_objects.basic_types.BOOL;
import wacc.frontend.identifier_objects.basic_types.CHAR;
import wacc.frontend.identifier_objects.basic_types.INT;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.SymbolTable;

public class UnaryOpExprAST extends ExpressionAST {

  private final ExpressionAST expr;
  private final String operator;
  private SymbolTable scopeST;

  public UnaryOpExprAST(List<WaccError> errors, ParserRuleContext ctx,
      ExpressionAST expr, String operator) {
    super(errors, ctx);
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

  private void checkNotParam(TYPE exprType) {

    if (!(exprType instanceof BOOL)) {

      addError(new MismatchedTypes(ctx, exprType, new BOOL()));

    }

    type = new BOOL();
  }

  private void checkNegateParam(TYPE exprType) {

    if (!(exprType instanceof INT)) {

      addError(new MismatchedTypes(ctx, exprType, new INT()));

    }

    type = new INT();
  }

  private void checkLengthParam(TYPE exprType) {

    if (!(exprType instanceof ARRAY)) {

      addError(new MismatchedTypes(ctx, exprType, new ARRAY(new TYPE())));

    }

    type = new INT();
  }

  private void checkChrParam(TYPE exprType) {

    if (!(exprType instanceof INT)) {

      addError(new MismatchedTypes(ctx, exprType, new INT()));

    }

    type = new CHAR();
  }

  private void checkOrdParam(TYPE exprType) {

    if (!(exprType instanceof CHAR)) {

      addError(new MismatchedTypes(ctx, exprType, new CHAR()));

    }

    type = new INT();
  }

  private void checkInvertParam(TYPE exprType) {

    if (!(exprType instanceof INT)) {

      addError(new MismatchedTypes(ctx, exprType, new INT()));

    }

    type = new INT();
  }

  private void checkReferenceParam(TYPE exprType) {

    type = new POINTER(exprType);

  }

  // malloc takes the size of the struct/type and returns
  // a pointer to the struct/var on the heap
  private void checkMallocParam(TYPE exprType) {

    if (!(exprType instanceof INT)) {
      addError(new MismatchedTypes(ctx, exprType, new INT()));
    }

    type = new POINTER(new TYPE());
  }


  /* ========================================================================= */


  @Override
  public void check() {

    scopeST = ST;
    expr.check();
    TYPE exprType = expr.getType();

    if (exprType == null) {
      // error occurred elsewhere
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
      case "~":
        checkInvertParam(exprType);
        break;
      case "&":
        checkReferenceParam(exprType);
        break;
      case "malloc":
        checkMallocParam(exprType);
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


