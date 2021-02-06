package semantic_parser.statements.assignments;

import antlr.WaccParser;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.FUNCTION;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import identifier_objects.basic_types.ARRAY;
import identifier_objects.basic_types.PAIR;
import semantic_parser.statements.assignments.expressions.SemanticExpressionParser;

public abstract class SemanticAssignmentParser extends SemanticExpressionParser {

  @Override
  public IDENTIFIER visitAssignLHS(WaccParser.AssignLHSContext ctx) {
    return visitIdentifier(ctx.IDENT().getText());
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
