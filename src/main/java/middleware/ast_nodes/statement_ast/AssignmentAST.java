package middleware.ast_nodes.statement_ast;

import middleware.NodeASTVisitor;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;


public class AssignmentAST extends StatementAST {

  private final LHSAssignAST LHS;
  private final RHSAssignAST RHS;

  public AssignmentAST(ParserRuleContext ctx, LHSAssignAST LHS, RHSAssignAST RHS) {
    super(ctx);
    this.LHS = LHS;
    this.RHS = RHS;
  }

  public LHSAssignAST getLHS() {
    return LHS;
  }

  public RHSAssignAST getRHS() {
    return RHS;
  }

  @Override
  public void check() {
    LHS.check();
    RHS.check();

    // LHS is an IDENTIFIER because it could be an Identifier(Ident).
    IDENTIFIER leftType = LHS.getType();

    // RHS must be a TYPE from the parser rules.
    TYPE rightType = RHS.getType();

    if (leftType != null && rightType != null) {

      if (!isCompatible(leftType, rightType)) {
        // LHS and RHS not type compatible.
        addError(new MismatchedTypes(ctx, rightType, leftType));
      }

    }

  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }


}
