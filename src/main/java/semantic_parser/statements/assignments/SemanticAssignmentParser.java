package semantic_parser.statements.assignments;

import antlr.WaccParser;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import identifier_objects.basic_types.ARRAY;
import identifier_objects.basic_types.PAIR;
import semantic_parser.statements.assignments.expressions.SemanticExpressionParser;

public abstract class SemanticAssignmentParser extends SemanticExpressionParser {

  @Override
  public TYPE visitAssignLHS(WaccParser.AssignLHSContext ctx) {
    IDENTIFIER identifier = visitIdentifier(ctx.IDENT().getText());
    if (identifier == null) {
      // identifier is undefined
      return null;
    }

    if (identifier instanceof VARIABLE) {
      // assignment LHS is not a valid TYPE
      return ((VARIABLE) identifier).getType();
    }

    if (identifier instanceof ARRAY) {
      // assignment LHS is not a valid TYPE
      return ((ARRAY) identifier).getType();
    }

    if (identifier instanceof PAIR) {
      if(ctx.pairElem().PAIR_FIRST() != null){
        return ((PAIR) identifier).getFirst();
      }else{
        return ((PAIR) identifier).getSecond();
      }
    }

    return null;
  }

  @Override
  public TYPE visitAssignRHS(WaccParser.AssignRHSContext ctx) {
    Object obj = visitChildren(ctx);
    if (obj == null) {
      // identifier is undefined
      return null;
    } else if (!(obj instanceof TYPE)) {
      // assignment LHS is not a valid TYPE
      return null;
    }
    return (TYPE) obj;
  }

}
