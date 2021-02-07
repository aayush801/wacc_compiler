package semantic_parser.statements.assignments.expressions;

import antlr.WaccParser;
import antlr.WaccParser.BracketedExprContext;
import antlr.WaccParser.PlusMinusOperationContext;
import antlr.WaccParser.UnaryOperationContext;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.unary_operator_functions.NEGATE;
import identifier_objects.unary_operator_functions.NOT;
import java.util.Collections;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;
import semantic_parser.statements.assignments.expressions.functions.SemanticFunctionParser;

public abstract class SemanticExpressionParser extends SemanticFunctionParser {


  /* ======================= BRACKETED EXPRESSION SEMANTICS ========================= */
  @Override
  public IDENTIFIER visitBracketedExpr(BracketedExprContext ctx) {
    return (IDENTIFIER) visit(ctx.expr());
  }

  /* ======================= BINARY EXPRESSION SEMANTICS ========================= */

  @Override
  public TYPE visitPlusMinusOperation(PlusMinusOperationContext ctx) {
    return visitFunctionCall(ctx, ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  @Override
  public TYPE visitDivMulModOperation(WaccParser.DivMulModOperationContext ctx) {
    return visitFunctionCall(ctx, ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  @Override
  public TYPE visitLogicalOrOperation(WaccParser.LogicalOrOperationContext ctx) {
    return visitFunctionCall(ctx, ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  @Override
  public TYPE visitGreLseComparison(WaccParser.GreLseComparisonContext ctx) {
    return visitFunctionCall(ctx, ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  @Override
  public TYPE visitEqNeqComparison(WaccParser.EqNeqComparisonContext ctx) {
    return visitFunctionCall(ctx, ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  @Override
  public TYPE visitLogicalAndOperation(WaccParser.LogicalAndOperationContext ctx) {
    return visitFunctionCall(ctx, ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  /* ======================= UNARY EXPRESSION SEMANTICS ========================= */

  @Override
  public TYPE visitUnaryOperation(UnaryOperationContext ctx) {
    /* @IMPORTANT
     *  Some unary functions have the same operator as the binary operators
     *  (e.g - is used for subtraction (1-2) and also negation (-2))
     *  hence we must explicitly call these function identifiers*/
    String funcIdentifier;

    if (ctx.op.MINUS() != null) {
      funcIdentifier = NEGATE.name;
    } else if (ctx.op.NOT() != null) {
      funcIdentifier = NOT.name;
    } else {
      funcIdentifier = ctx.unaryOperator().getText();
    }

    return visitFunctionCall(ctx, funcIdentifier, Collections.singletonList(ctx.expr()));
  }

}
