package front_end;

import antlr.WaccParser;
import antlr.WaccParser.BooleanContext;
import antlr.WaccParser.CharacterContext;
import antlr.WaccParser.IdentifierContext;
import antlr.WaccParser.IntegerContext;
import antlr.WaccParser.PairContext;
import antlr.WaccParser.ParamContext;
import antlr.WaccParser.PlusMinusOperationContext;
import antlr.WaccParser.StringContext;
import antlr.WaccParser.TypeContext;
import antlr.WaccParser.UnaryOperationContext;
import identifier_objects.FUNCTION;
import identifier_objects.IDENTIFIER;
import identifier_objects.PARAM;
import identifier_objects.TYPE;
import identifier_objects.basic_types.BOOL;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
import identifier_objects.basic_types.PAIR;
import identifier_objects.basic_types.STR;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class SemanticParser extends SemanticBaseParser {

  /* =========================== Semantic checker for literals ============================*/

  @Override
  public IDENTIFIER visitIdentifier(IdentifierContext ctx) {
    return ST.lookupAll(ctx.IDENT().getText());
  }

  @Override
  public CHAR visitCharacter(CharacterContext ctx) {
    return (CHAR) ST.lookupAll("char");
  }

  @Override
  public BOOL visitBoolean(BooleanContext ctx) {
    return (BOOL) ST.lookupAll("bool");
  }

  @Override
  public STR visitString(StringContext ctx) {
    return (STR) ST.lookupAll("string");
  }

  @Override
  public INT visitInteger(IntegerContext ctx) {
    return (INT) ST.lookupAll("int");
  }

  @Override
  public PAIR visitPair(PairContext ctx) {
    return (PAIR) ST.lookupAll("pair");
  }

  @Override
  public TYPE visitType(TypeContext ctx) {
    return (TYPE) ST.lookupAll(ctx.getText());
  }

  @Override
  public PARAM visitParam(ParamContext ctx) {
    String identifier = ctx.IDENT().getText();
    if (ST.lookup(identifier) != null) {
      // if the identifier has already been used return an error
      System.out.println("Error Duplicate identifier " + identifier);
      return null;
    }
    // get the type
    TYPE type = visitType(ctx.type());
    PARAM param = new PARAM(type);
    // add newly defined variable to scope
    ST.add(ctx.IDENT().getText(), param);
    return param;
  }

  /* ======================= BINARY EXPRESSION SEMANTICS ========================= */


  private FUNCTION visitBinaryOperation(ParserRuleContext ctx) {
    if (ctx.getChildCount() != 3) {
      System.out.println("Binary Operator only takes two parameters");
      return null;
    }
    ParseTree lExpr = ctx.getChild(0);
    ParseTree rExpr = ctx.getChild(2);
    String operatorIdentifier = ctx.getChild(1).getText();
    return visitFunction(operatorIdentifier, new ParseTree[]{lExpr, rExpr});
  }

  @Override
  public FUNCTION visitPlusMinusOperation(PlusMinusOperationContext ctx) {
    return visitBinaryOperation(ctx);
  }

  @Override
  public FUNCTION visitDivMulModOperation(WaccParser.DivMulModOperationContext ctx) {
    return visitBinaryOperation(ctx);
  }

  @Override
  public FUNCTION visitLogicalOrOperation(WaccParser.LogicalOrOperationContext ctx) {
    return visitBinaryOperation(ctx);
  }

  @Override
  public FUNCTION visitGreLseComparison(WaccParser.GreLseComparisonContext ctx) {
    return visitBinaryOperation(ctx);
  }

  @Override
  public FUNCTION visitEqNeqComparison(WaccParser.EqNeqComparisonContext ctx) {
    return visitBinaryOperation(ctx);
  }

  @Override
  public FUNCTION visitLogicalAndOperation(WaccParser.LogicalAndOperationContext ctx) {
    return visitBinaryOperation(ctx);
  }

  /* ======================= UNARY EXPRESSION SEMANTICS ========================= */


  @Override
  public FUNCTION visitUnaryOperation(UnaryOperationContext ctx) {
    if (ctx.getChildCount() != 2) {
      System.out.println("Unary Operator only takes one parameter");
      return null;
    }
    String operatorIdentifier = ctx.unaryOperator().getText();
    ParseTree expr = ctx.getChild(1);
    return visitFunction(operatorIdentifier, new ParseTree[]{expr});
  }

}
