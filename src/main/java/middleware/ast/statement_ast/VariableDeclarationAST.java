package middleware.ast.statement_ast;

import identifier_objects.*;

import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.VARIABLE;
import middleware.ast.arrays_ast.TypeAST;
import org.antlr.v4.runtime.Token;

public class VariableDeclarationAST extends StatementAST {

  private final TypeAST typeAST;
  private final String varName;
  private final RHSAssignAST RHS;
  public VARIABLE varObj;

  public VariableDeclarationAST(Token token, TypeAST typeAST, String varName,
      RHSAssignAST RHS) {
    super(token);
    this.typeAST = typeAST;
    this.varName = varName;
    this.RHS = RHS;
  }

  @Override
  public void check() {
    // check the type of the declaration.
    typeAST.check();

    // verify that the type is valid.
    if (typeAST.getType() == null) {
      addError(new Undefined(token, typeAST.token.getText()));
      return;
    }

    // lookup name in current symbol table.
    IDENTIFIER variable = ST.lookup(varName);

    // if variable already present in current symbol table and is not a
    // function, this is not valid as it is a duplicate identifier.
    if (variable != null && !(variable instanceof FUNCTION)) {
      addError(new DuplicateIdentifier(token));
      return;
    }

    // check the RHS now.
    RHS.check();

    // verify that the RHS is not null.
    if (RHS.getType() == null) {
      addError(new Undefined(RHS.token));
      return;
    }

    // Verify that LHS and RHS are type compatible.
    if (!isCompatible(typeAST.getType(), RHS.getType())) {
      addError(new MismatchedTypes(token, typeAST.getType(), RHS.getType()));
      return;
    }

    // If all checks pass, create new VARIABLE object with the type of the
    // typeAST node, and add this to the current symbol table.
    varObj = new VARIABLE(typeAST.getType());

    ST.add(varName, varObj);
  }
}
