package middleware.ast_nodes.statement_ast;

import middleware.NodeASTVisitor;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ReadAST extends StatementAST {

  private final LHSAssignAST LHS;

  public ReadAST(ParserRuleContext ctx, LHSAssignAST LHS) {
    super(ctx);
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
