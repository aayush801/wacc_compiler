package semantic_parser.statements.assignments.expressions;

import antlr.WaccParser.ExprContext;
import identifier_objects.TYPE;
import identifier_objects.unary_operator_functions.NEGATE;
import identifier_objects.unary_operator_functions.NOT;
import java.util.Arrays;
import java.util.Collections;

import middleware.ast.expression_ast.ExpressionAST;
import semantic_parser.statements.assignments.expressions.functions.SemanticFunctionParser;

public abstract class SemanticExpressionParser extends SemanticFunctionParser {

  /* ======================= BRACKETED EXPRESSION SEMANTICS ========================= */
  @Override
  public TYPE visitExpr(ExprContext ctx) {
    /* ======================= BINARY EXPRESSION SEMANTICS ========================= */
    if (ctx.binaryOperator != null) {
      String operator = ctx.binaryOperator.getText();
      TYPE type = visitFunctionCall(ctx, operator,
              Arrays.asList(ctx.expr(0), ctx.expr(1)));
      //Now we need a way to get the children's AST nodes
//      ExpressionAST left = ...;
//      ExpressionAST right = ...;

      ExpressionAST left = new ExpressionAST((TYPE) visit(ctx.expr(0)));
      ExpressionAST right = new ExpressionAST((TYPE) visit(ctx.expr(1)));
      ExpressionAST expressionAST = new ExpressionAST(type, left, operator, right);
      return type;
    }

    /* ======================= UNARY EXPRESSION SEMANTICS ========================= */
    /* @IMPORTANT
     *  Some unary functions have the same operator as the binary operators
     *  (e.g - is used for subtraction (1-2) and also negation (-2))
     *  hence we must explicitly call these function identifiers */
    if (ctx.unaryOperator() != null) {
      String unOpIdentifier;
      if (ctx.unaryOperator().NOT() != null) {
        unOpIdentifier = NOT.name;
      } else if (ctx.unaryOperator().MINUS() != null) {
        unOpIdentifier = NEGATE.name;
      } else {
        unOpIdentifier = ctx.unaryOperator().getText();
      }

      return visitFunctionCall(ctx, unOpIdentifier, Collections.singletonList(ctx.expr(0)));
    }

    // check expression enclosed inside brackets
    if (ctx.OPEN_PARENTHESES() != null) {
      return visitExpr(ctx.expr(0));
    }

    if (ctx.arrayElem() != null) {
      return visitArrayElem(ctx.arrayElem());
    }

    /* ============ check literals and identifiers ============ */

    Object obj = visitChildren(ctx);
    if (!(obj instanceof TYPE)) {
      return null;
    }
    return (TYPE) obj;
  }
}
