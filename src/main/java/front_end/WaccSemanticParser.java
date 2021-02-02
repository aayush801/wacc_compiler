package front_end;

import antlr.BasicParser;
import antlr.BasicParser.*;
import antlr.BasicParserBaseVisitor;
import org.antlr.v4.runtime.tree.*;



public class WaccSemanticParser extends BasicParserBaseVisitor<WACCTypes> {

  @Override
  public WACCTypes visitProg(ProgContext ctx) {
    //System.out.println("Hello");
    return visitChildren(ctx);
  }

  @Override
  public WACCTypes visitExpr(BasicParser.ExprContext ctx) {
    //System.out.println("we have an expression");
    return visitChildren(ctx);
  }

  // ========================  term2 checks ===================================

  @Override public WACCTypes visitTimesDivide(BasicParser.TimesDivideContext ctx) {
    boolean childrenOK = checkTypes(WACCTypes.INT, ctx.getChild(0), ctx.getChild(2));

      if (childrenOK) {
        return WACCTypes.INT;
      }
      //cry
      System.out.println("Something went wrong");
      return WACCTypes.ERROR;
    }

  @Override public WACCTypes visitGotoTerm1(BasicParser.GotoTerm1Context ctx) {
     return visitChildren(ctx);  // OK??
  }



  // ==========================================================================


  // ========================  term1 checks ===================================

  @Override public WACCTypes visitPlusMinus(BasicParser.PlusMinusContext ctx) {
    boolean childrenOK = checkTypes(WACCTypes.INT, ctx.getChild(0), ctx.getChild(2));

    if (childrenOK) {
      return WACCTypes.INT;
    }
    //cry
    System.out.println("Something went wrong");
    return WACCTypes.ERROR;
  }

  @Override public WACCTypes visitGotoFactor(BasicParser.GotoFactorContext ctx) {
    return visitChildren(ctx);  // OK??
  }

  // ==========================================================================

  // ========================  factor checks ==================================
  @Override
  public WACCTypes visitVarNum(BasicParser.VarNumContext ctx) {
    //lookup in ST, verify that it exists in the scope(and outer STs as well), then check its type.
    String thingy = ctx.getText(); // this is the name of the var
    System.out.println(thingy);
    return WACCTypes.INT; //verified
  }

  @Override public WACCTypes visitExprThatGivesAnInt(BasicParser.ExprThatGivesAnIntContext ctx) {
    Boolean childOK = checkTypes(WACCTypes.INT, ctx.getChild(1));
    if (childOK) {
      return WACCTypes.INT;
    }
    //cry
    System.out.println("Something went wrong");
    return WACCTypes.ERROR;
  }

  // ==========================================================================

  @Override public WACCTypes visitNum(BasicParser.NumContext ctx) {
    return WACCTypes.INT;
  }

  @Override
  public WACCTypes visitComparison(BasicParser.ComparisonContext ctx) {
    ParseTree child1 = ctx.getChild(0);
    ParseTree child2 = ctx.getChild(2);

    Boolean typesMatch = matchTypes(child1, child2);

    if(typesMatch) {
      return WACCTypes.BOOL;
    }
    //cry
    System.out.println("Something went wrong");
    return WACCTypes.ERROR;
  }

  private Boolean checkTypes(WACCTypes expectedType, ParseTree ... trees) {
    for (ParseTree tree: trees) {
      WACCTypes thisType = getType(tree);
      if (thisType != expectedType) {
        System.out.println("Expected type: " + expectedType + ", Actual type: " + thisType);
        return false;
      }
    }
    return true;
  }

  private WACCTypes getType(ParseTree tree) {
    WaccSemanticParser tempParser = new WaccSemanticParser();
    return tempParser.visit(tree);
  }

  private Boolean matchTypes(ParseTree tree1, ParseTree tree2) {
    WACCTypes type1 = getType(tree1);
    WACCTypes type2 = getType(tree2);
    Boolean typesMatch = type1 == type2;
    if (!typesMatch) {
      System.out.println("Expected: " + type1 + ", actual: " + type2);
    }
    return typesMatch;
  }
}
