package front_end;

import static antlr.WaccLexer.CHR;
import static antlr.WaccLexer.LENGTH;
import static antlr.WaccLexer.MINUS;
import static antlr.WaccLexer.NOT;
import static antlr.WaccLexer.ORD;

import antlr.WaccParser.DivMulModOperationContext;
import antlr.WaccParser.EqNeqComparisonContext;
import antlr.WaccParser.ExprContext;
import antlr.WaccParser.GreLseComparisonContext;
import antlr.WaccParser.LogicalAndOperationContext;
import antlr.WaccParser.LogicalOrOperationContext;
import antlr.WaccParser.PlusMinusOperationContext;
import antlr.WaccParser.UnaryOperationContext;
import org.antlr.v4.runtime.tree.ParseTree;
import symbol_table.SymbolTable;

public class WaccSemanticeExpressionParser extends WaccSemanticBaseParser {

  protected SymbolTable symTab;

  public WaccSemanticeExpressionParser(SymbolTable st) {
    this.symTab = st;
  }

  /* ======================= BINARY EXPRESSION SEMANTICS ========================= */
  private WaccType visitBinaryOperation(ExprContext ctx, WaccType type) {
    ParseTree lexpr = ctx.getChild(0);
    ParseTree rexpr = ctx.getChild(2);

    if (type == null) {
      type = getType(lexpr);
    }

    if (this.checkTypesCompatible(type, lexpr, rexpr)) {
      return type;
    } else {
      // temp message need to update with better in future
      System.out.println("Error incompatible types: " + lexpr.getText() + ", " + rexpr.getText());
      return null;
    }
  }

  @Override
  public WaccType visitPlusMinusOperation(PlusMinusOperationContext ctx) {
    return visitBinaryOperation(ctx, WaccType.INT);
  }

  @Override
  public WaccType visitDivMulModOperation(DivMulModOperationContext ctx) {
    return visitBinaryOperation(ctx, WaccType.INT);
  }

  @Override
  public WaccType visitLogicalAndOperation(LogicalAndOperationContext ctx) {
    return visitBinaryOperation(ctx, WaccType.BOOL);
  }

  @Override
  public WaccType visitLogicalOrOperation(LogicalOrOperationContext ctx) {
    return visitBinaryOperation(ctx, WaccType.BOOL);
  }

  @Override
  public WaccType visitGreLseComparison(GreLseComparisonContext ctx) {
    return visitBinaryOperation(ctx, null);
  }

  @Override
  public WaccType visitEqNeqComparison(EqNeqComparisonContext ctx) {
    return visitBinaryOperation(ctx, null);
  }

  /* ======================= UNARY EXPRESSION SEMANTICS ========================= */
  private WaccType visitUnaryOperation(ExprContext ctx, WaccType type){
    ParseTree child = ctx.getChild(1);
    if (this.checkTypesCompatible(type, child)) {
      return type;
    } else {
      System.out.println("Error incompatible types: unary operation only takes type "+ type.toString());
      return null;
    }
  }

  @Override
  public WaccType visitUnaryOperation(UnaryOperationContext ctx) {
    switch (ctx.unaryOperator().getStart().getType()) {
      case NOT:
         return visitUnaryOperation(ctx, WaccType.BOOL);
      case MINUS:
      case CHR:
        return visitUnaryOperation(ctx, WaccType.INT);
      case LENGTH:
        return visitUnaryOperation(ctx, WaccType.ARRAY);
      case ORD:
        return visitUnaryOperation(ctx, WaccType.CHAR);
    }
    return null;
  }

  //
  //  @Override
  //  public WaccTypes visitProg(ProgContext ctx) {
  //    //System.out.println("Hello");
  //    return visitChildren(ctx);
  //  }
  //
  //  @Override
  //  public WaccTypes visitExpr(ExprContext ctx) {
  //    //System.out.println("we have an expression");
  //    return visitChildren(ctx);
  //  }
  //
  //  // ========================  term2 checks ===================================
  //
  //  @Override public WaccTypes visitTimesDivide(BasicParser.TimesDivideContext ctx) {
  //    boolean childrenOK = checkTypes(WaccTypes.INT, ctx.getChild(0), ctx.getChild(2));
  //
  //      if (childrenOK) {
  //        return WaccTypes.INT;
  //      }
  //      //cry
  //      System.out.println("Something went wrong");
  //      return WaccTypes.ERROR;
  //    }
  //
  //  @Override public WaccTypes visitGotoTerm1(BasicParser.GotoTerm1Context ctx) {
  //     return visitChildren(ctx);  // OK??
  //  }
  //
  //
  //
  //  // ==========================================================================
  //
  //
  //  // ========================  term1 checks ===================================
  //
  //  @Override public WaccTypes visitPlusMinus(BasicParser.PlusMinusContext ctx) {
  //    boolean childrenOK = checkTypes(WaccTypes.INT, ctx.getChild(0), ctx.getChild(2));
  //
  //    if (childrenOK) {
  //      return WaccTypes.INT;
  //    }
  //    //cry
  //    System.out.println("Something went wrong");
  //    return WaccTypes.ERROR;
  //  }
  //
  //  @Override public WaccTypes visitGotoFactor(BasicParser.GotoFactorContext ctx) {
  //    return visitChildren(ctx);  // OK??
  //  }
  //
  //  // ==========================================================================
  //
  //  // ========================  factor checks ==================================
  //  @Override
  //  public WaccTypes visitVarNum(BasicParser.VarNumContext ctx) {
  //    //lookup in ST, verify that it exists in the scope(and outer STs as well), then check its
  // type.
  //    String thingy = ctx.getText(); // this is the name of the var
  //    System.out.println(thingy);
  //    return WaccTypes.INT; //verified
  //  }
  //
  //  @Override public WaccTypes visitExprThatGivesAnInt(BasicParser.ExprThatGivesAnIntContext ctx)
  // {
  //    Boolean childOK = checkTypes(WaccTypes.INT, ctx.getChild(1));
  //    if (childOK) {
  //      return WaccTypes.INT;
  //    }
  //    //cry
  //    System.out.println("Something went wrong");
  //    return WaccTypes.ERROR;
  //  }
  //
  //  // ==========================================================================
  //
  //  @Override public WaccTypes visitNum(BasicParser.NumContext ctx) {
  //    return WaccTypes.INT;
  //  }
  //
  //  @Override
  //  public WaccTypes visitComparison(BasicParser.ComparisonContext ctx) {
  //    ParseTree child1 = ctx.getChild(0);
  //    ParseTree child2 = ctx.getChild(2);
  //
  //    Boolean typesMatch = matchTypes(child1, child2);
  //
  //    if(typesMatch) {
  //      return WaccTypes.BOOL;
  //    }
  //    //cry
  //    System.out.println("Something went wrong");
  //    return WaccTypes.ERROR;
  //  }
  //
  //  private Boolean checkTypes(WaccTypes expectedType, ParseTree ... trees) {
  //    for (ParseTree tree: trees) {
  //      WaccTypes thisType = getType(tree);
  //      if (thisType != expectedType) {
  //        System.out.println("Expected type: " + expectedType + ", Actual type: " + thisType);
  //        return false;
  //      }
  //    }
  //    return true;
  //  }
  //
  //  private WaccTypes getType(ParseTree tree) {
  //    WaccSemanticParser tempParser = new WaccSemanticParser();
  //    return tempParser.visit(tree);
  //  }
  //
  //  private Boolean matchTypes(ParseTree tree1, ParseTree tree2) {
  //    WaccTypes type1 = getType(tree1);
  //    WaccTypes type2 = getType(tree2);
  //    Boolean typesMatch = type1 == type2;
  //    if (!typesMatch) {
  //      System.out.println("Expected: " + type1 + ", actual: " + type2);
  //    }
  //    return typesMatch;
  //  }
}
