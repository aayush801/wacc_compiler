package semantic_parser.statements.assignments;

import antlr.WaccParser;
import identifier_objects.IDENTIFIER;
import identifier_objects.basic_types.ARRAY;
import semantic_parser.statements.assignments.expressions.SemanticExpressionParser;

public abstract class SemanticAssignmentParser extends SemanticExpressionParser {

  @Override
  public IDENTIFIER visitAssignLHS(WaccParser.AssignLHSContext ctx) {
    IDENTIFIER identifier = visitIdentifier(ctx.IDENT().getText());

    if(identifier == null){
      System.out.println(ctx.IDENT().getText() + " is null");
      return null;
    }
    return identifier;
  }

  @Override
  public IDENTIFIER visitAssignRHS(WaccParser.AssignRHSContext ctx) {
    return (IDENTIFIER) visitChildren(ctx);
  }
}
