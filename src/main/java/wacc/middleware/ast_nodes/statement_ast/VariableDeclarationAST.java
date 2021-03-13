package wacc.middleware.ast_nodes.statement_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.DuplicateIdentifier;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.frontend.identifier_objects.FUNCTION;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.VARIABLE;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.TypeAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class VariableDeclarationAST extends StatementAST {

  private final TypeAST typeAST;
  private final String varName;
  private final RHSAssignAST rhsAssignAST;
  public VARIABLE varObj;

  public VariableDeclarationAST(List<WaccError> errors, ParserRuleContext ctx, TypeAST typeAST,
      String varName, RHSAssignAST rhsAssignAST) {
    super(errors, ctx);
    this.typeAST = typeAST;
    this.varName = varName;
    this.rhsAssignAST = rhsAssignAST;
  }

  @Override
  public void check() {
    // check the type of the declaration.
    typeAST.check();

    // verify that the type is valid.
    if (typeAST.getType() == null) {
      return;
    }

    // lookup name in current symbol table.
    IDENTIFIER variable = NodeAST.ST.lookup(varName);

    // if variable already present in current symbol table and is not a
    // function, this is not valid as it is a duplicate identifier.
    if (variable != null && !(variable instanceof FUNCTION)) {
      addError(new DuplicateIdentifier(ctx));
      return;
    }

    // check the RHS now.
    rhsAssignAST.check();

    // verify that the RHS is not null.
    if (rhsAssignAST.getType() == null) {
      return;
    }

    // Verify that LHS and RHS are type compatible.
    if (!isCompatible(typeAST.getType(), rhsAssignAST.getType())) {

      addError(new MismatchedTypes(ctx, rhsAssignAST.getType(),
          typeAST.getType()));
      return;

    }

    // If all checks pass, create new VARIABLE object with the type of the
    // typeAST node, and add this to the current symbol table.
    varObj = new VARIABLE(typeAST.getType());

    NodeAST.ST.add(varName, varObj);
  }

  public TypeAST getTypeAST() {
    return typeAST;
  }

  public RHSAssignAST getRhsAssignAST() {
    return rhsAssignAST;
  }

  public VARIABLE getVarObj() {
    return varObj;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}