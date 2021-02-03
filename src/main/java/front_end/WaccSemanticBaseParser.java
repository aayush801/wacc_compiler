package front_end;

import antlr.WaccParser;
import antlr.WaccParser.IdentifierContext;
import antlr.WaccParserBaseVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class WaccSemanticBaseParser extends WaccParserBaseVisitor<WaccType> {

  /* ========= PRIVATE HELPER METHODS =============== */

  protected WaccType getType(ParseTree tree) {
    return this.visit(tree);
  }

  // this function can be updated for more complicated compatibility (e.g pairs)
  boolean isCompatible(WaccType type1, WaccType type2) {
    return type1 == type2;
  }

  boolean checkTypesCompatible(ParseTree... trees) {
    WaccType expectedType = getType(trees[0]);
    return checkTypesCompatible(expectedType, trees);
  }

  Boolean checkTypesCompatible(WaccType expectedType, ParseTree... trees) {
    // keep if block for DEBUGGING only
    if (trees == null) {
      System.out.println("Null tree given in compatible types function");
      return false;
    }

    for (ParseTree tree : trees) {
      WaccType treeType = getType(tree);
      if (!isCompatible(treeType, expectedType)) {
        System.out.println("Expected type: " + expectedType + ", Actual type: " + treeType);
        return false;
      }
    }
    return true;
  }

  /* =========================== Semantic checker for literals ============================*/

  @Override
  public WaccType visitIdentifier(IdentifierContext ctx) {
    return WaccType.IDENT;
  }

  @Override
  public WaccType visitCharacter(WaccParser.CharacterContext ctx) {
    return WaccType.CHAR;
  }

  @Override
  public WaccType visitBoolean(WaccParser.BooleanContext ctx) {
    return WaccType.BOOL;
  }

  @Override
  public WaccType visitString(WaccParser.StringContext ctx) {
    return WaccType.STRING;
  }

  @Override
  public WaccType visitInteger(WaccParser.IntegerContext ctx) {
    return WaccType.INT;
  }

  @Override
  public WaccType visitPair(WaccParser.PairContext ctx) {
    return WaccType.PAIR;
  }
}
