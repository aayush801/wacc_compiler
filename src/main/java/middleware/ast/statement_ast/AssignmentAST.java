package middleware.ast.statement_ast;

import identifier_objects.VARIABLE;
import middleware.ast.expression_ast.ExpressionAST;

public class AssignmentAST extends StatementAST {

  private final String varname;
  private final ExpressionAST expr;
  private VARIABLE varObj;

  public AssignmentAST(String varname, ExpressionAST expr) {
    this.varname = varname;
    this.expr = expr;
  }
}
