package wacc.middleware.ast_nodes.statement_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.basic_types.CHAR;
import wacc.frontend.identifier_objects.basic_types.INT;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ReadAST extends StatementAST {

  private final LHSAssignAST LHS;

  public ReadAST(List<WaccError> errors, ParserRuleContext ctx, LHSAssignAST LHS) {
    super(errors, ctx);
    this.LHS = LHS;
  }

  public LHSAssignAST getLHS() {
    return LHS;
  }

  @Override
  public void check() {

    // Verify that the LHS is a valid assignLHS.
    LHS.check();

    // Verify that type is an INT or CHAR.
    IDENTIFIER type = LHS.getType();

    if (type != null) {

      if (!(type instanceof INT || type instanceof CHAR)) {

        addError(new MismatchedTypes(ctx, type, new INT(), new CHAR()));

      }
    }
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
