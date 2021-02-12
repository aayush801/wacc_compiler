package middleware.ast.statement_ast;

import identifier_objects.*;

import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.VARIABLE;
import middleware.ast.types_ast.TypeAST;
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
    typeAST.check();

    if (typeAST.getType() == null) {
      addError(new Undefined(token, typeAST.token.getText()));
      return;
    }

    IDENTIFIER variable = ST.lookup(varName);

    if (variable != null && !(variable instanceof FUNCTION)) {
      addError(new DuplicateIdentifier(token));
      return;
    }

    RHS.check();
    if (RHS.getType() == null) {
      addError(new Undefined(RHS.token));
      return;
    }

    if (!isCompatible(typeAST.getType() ,RHS.getType() )) {
      addError(new MismatchedTypes(token, RHS.getType(), typeAST.getType()));
      return;
    }

    varObj = new VARIABLE(typeAST.getType());

    ST.add(varName, varObj);
  }
}
