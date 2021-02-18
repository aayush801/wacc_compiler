package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.FUNCTION;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.VARIABLE;
import java.util.List;
import middleware.NodeAST;
import middleware.types_ast.TypeAST;
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
      return;
    }

    // lookup name in current symbol table.
    IDENTIFIER variable = NodeAST.ST.lookup(varName);

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
      return;
    }

    // Verify that LHS and RHS are type compatible.
    if (!isCompatible(typeAST.getType(), RHS.getType())) {

      addError(new MismatchedTypes(token, RHS.getType(), typeAST.getType()));
      return;

    }

    // If all checks pass, create new VARIABLE object with the type of the
    // typeAST node, and add this to the current symbol table.
    varObj = new VARIABLE(typeAST.getType());

    NodeAST.ST.add(varName, varObj);
  }

}
