package middleware.ast.statement_ast;

import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import middleware.ast.AbstractSyntaxTree;

import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.VARIABLE;
import identifier_objects.basic_types.ARRAY;
import middleware.ast.arrays_ast.TypeAST;
import org.antlr.v4.runtime.Token;

public class VariableDeclarationAST extends StatementAST {

  private final TypeAST typeAST;
  private final String varname;
  private final RHSAssignAST RHS;
  public VARIABLE varObj;

  public VariableDeclarationAST(Token token, TypeAST typeAST, String varname, RHSAssignAST RHS) {
    super(token);
    this.typeAST = typeAST;
    this.varname = varname;
    this.RHS = RHS;
  }

  @Override
  public void check() {
    typeAST.check();
    if (typeAST.getType() == null) {
      addError(new Undefined(token, typeAST.token.getText()));
      return;
    }

    IDENTIFIER variable = ST.lookup(varname);

    if (variable != null) {
      addError(new DuplicateIdentifier(token));
      return;
    }

    RHS.check();
    if(RHS.getType() == null){
      addError(new Undefined(RHS.token));
      return;
    }

   // System.out.println(RHS.getType());
    //System.out.println(typeAST.getType());
    if(!isCompatible(typeAST.getType(), RHS.getType())){
      addError(new MismatchedTypes(token, typeAST.getType(), RHS.getType()));
      return;
    }

    varObj = new VARIABLE(typeAST.getType());

    ST.add(varname, varObj);
  }
}
