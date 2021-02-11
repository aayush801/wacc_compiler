package middleware.ast.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class AssignmentAST extends StatementAST {

  private final String varname;
  private final ExpressionAST exprAST;
  private VARIABLE varObj;

  public AssignmentAST(Token token, String varname, ExpressionAST exprAST) {
    super(token);
    this.varname = varname;
    this.exprAST = exprAST;
  }

  @Override
  public void check() {
    IDENTIFIER variable = ST.lookupAll(varname);
    exprAST.check(); // will get type of expr
    if (variable == null) addError(new Undefined(token, varname));
    else if (!(variable instanceof VARIABLE))
      addError(new MismatchedTypes(token, variable, new VARIABLE(new EXPR())));
    else if (!isCompatible(((VARIABLE) variable).getType(), exprAST.type))
      addError(new MismatchedTypes(token, ((VARIABLE) variable).getType(), exprAST.type));
    else ST.add(varname, varObj);
  }
}
