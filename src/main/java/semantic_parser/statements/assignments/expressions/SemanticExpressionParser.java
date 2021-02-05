package semantic_parser.statements.assignments.expressions;

import antlr.WaccParser;
import antlr.WaccParser.BracketedExprContext;
import antlr.WaccParser.PlusMinusOperationContext;
import antlr.WaccParser.UnaryOperationContext;
import semantic_parser.statements.assignments.expressions.functions.SemanticFunctionParser;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import java.util.Collections;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class SemanticExpressionParser extends SemanticFunctionParser {


  /* ======================= BRACKETED EXPRESSION SEMANTICS ========================= */
  @Override
  public IDENTIFIER visitBracketedExpr(BracketedExprContext ctx) {
    return (IDENTIFIER) visit(ctx.expr());
  }

  /* ======================= BINARY EXPRESSION SEMANTICS ========================= */

  @Override
  public TYPE visitPlusMinusOperation(PlusMinusOperationContext ctx) {
    return visitFunctionCall(ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  @Override
  public TYPE visitDivMulModOperation(WaccParser.DivMulModOperationContext ctx) {
    return visitFunctionCall(ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  @Override
  public TYPE visitLogicalOrOperation(WaccParser.LogicalOrOperationContext ctx) {
    return visitFunctionCall(ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  @Override
  public TYPE visitGreLseComparison(WaccParser.GreLseComparisonContext ctx) {
    return visitFunctionCall(ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  @Override
  public TYPE visitEqNeqComparison(WaccParser.EqNeqComparisonContext ctx) {
    return visitFunctionCall(ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  @Override
  public TYPE visitLogicalAndOperation(WaccParser.LogicalAndOperationContext ctx) {
    return visitFunctionCall(ctx.op.getText(),
        ctx.expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  /* ======================= UNARY EXPRESSION SEMANTICS ========================= */

  @Override
  public TYPE visitUnaryOperation(UnaryOperationContext ctx) {
    return visitFunctionCall(ctx.unaryOperator().getText(), Collections.singletonList(ctx.expr()));
  }

}
