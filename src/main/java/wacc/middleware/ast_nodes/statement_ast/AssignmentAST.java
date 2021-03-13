package wacc.middleware.ast_nodes.statement_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;


public class AssignmentAST extends StatementAST {

  private final LHSAssignAST LHS;
  private final RHSAssignAST RHS;

  public AssignmentAST(List<WaccError> errors, ParserRuleContext ctx,
      LHSAssignAST LHS, RHSAssignAST RHS) {
    super(errors, ctx);
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
