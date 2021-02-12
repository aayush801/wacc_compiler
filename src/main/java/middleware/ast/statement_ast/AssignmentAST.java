package middleware.ast.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import org.antlr.v4.runtime.Token;


public class AssignmentAST extends StatementAST {

  private final LHSAssignAST LHS;
  private final RHSAssignAST RHS;

  public AssignmentAST(Token token, LHSAssignAST LHS, RHSAssignAST RHS) {
    super(token);
    this.LHS = LHS;
    this.RHS = RHS;
  }

  @Override
  public void check() {
    LHS.check();
    RHS.check();

    // LHS is an IDENTIFIER because it could be an Identifier(Ident).
    IDENTIFIER leftType = LHS.getType();

    // RHS must be a TYPE from the parser rules.
    TYPE rightType = RHS.getType();

    if (leftType == null) {
      // Type of LHS is undefined.
      addError(new Undefined(LHS.token));
    } else if (rightType == null) {
      // Type of RHS is undefined.
      addError(new Undefined(RHS.token));
    } else if (!isCompatible(leftType, rightType)) {
      // LHS and RHS not type compatible.
      addError(new MismatchedTypes(token, rightType, leftType));
    }
  }
}
