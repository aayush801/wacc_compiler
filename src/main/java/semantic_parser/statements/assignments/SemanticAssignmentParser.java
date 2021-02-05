package semantic_parser.statements.assignments;

import antlr.WaccParser;
import identifier_objects.IDENTIFIER;
import semantic_parser.statements.assignments.expressions.SemanticExpressionParser;
import identifier_objects.TYPE;

public class SemanticAssignmentParser extends SemanticExpressionParser {

  @Override
  public TYPE visitAssignLHS(WaccParser.AssignLHSContext ctx) {
    IDENTIFIER identifier = visitIdentifier(ctx.IDENT().getText());
    if(identifier == null){
      // identifier is undefined
      return null;
    }else if(!(identifier instanceof TYPE)){
      // assignment LHS is not a valid TYPE
      return null;
    }
    return (TYPE) identifier;
  }

  @Override
  public TYPE visitAssignRHS(WaccParser.AssignRHSContext ctx) {
    Object obj = visitChildren(ctx);
    if(obj == null){
      // identifier is undefined
      return null;
    }else if(!(obj instanceof TYPE)){
      // assignment LHS is not a valid TYPE
      return null;
    }
    return (TYPE) obj;
  }

}
