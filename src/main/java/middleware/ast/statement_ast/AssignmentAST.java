package middleware.ast.statement_ast;

import identifier_objects.VARIABLE;
import middleware.ast.expression_ast.ExpressionAST;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.VARIABLE;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.expression_ast.ExpressionAST;
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
//    if (variable == null) addError(new Undefined(token, varname));
//    else if (!(variable instanceof VARIABLE))
//      addError(new MismatchedTypes(token, variable, new VARIABLE(new EXPR())));
//    else if (!isCompatible(((VARIABLE) variable).getType(), exprAST.getType()))
//      addError(new MismatchedTypes(token, ((VARIABLE) variable).getType(), exprAST.getType()));
//    else {
//      ST.add(varname, variable);
//    }
  }
}
