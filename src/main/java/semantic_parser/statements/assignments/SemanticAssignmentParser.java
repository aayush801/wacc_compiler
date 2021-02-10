package semantic_parser.statements.assignments;

import antlr.WaccParser;
import identifier_objects.IDENTIFIER;
import semantic_parser.statements.assignments.expressions.SemanticExpressionParser;

public abstract class SemanticAssignmentParser extends SemanticExpressionParser {

  @Override
  public IDENTIFIER visitAssignLHS(WaccParser.AssignLHSContext ctx) {
    if(ctx.IDENT() != null) {
      return visitIdentifier(ctx.IDENT().getText());
    }

    if(ctx.arrayElem() != null){
      return visitArrayElem(ctx.arrayElem());
    }

    if(ctx.pairElem() != null){
      return visitPairElem(ctx.pairElem());
    }

    return null;
  }

  @Override
  public IDENTIFIER visitAssignRHS(WaccParser.AssignRHSContext ctx) {
    return (IDENTIFIER) visitChildren(ctx);
  }
}
