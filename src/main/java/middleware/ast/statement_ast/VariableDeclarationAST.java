package middleware.ast.statement_ast;

import identifier_objects.TYPE;
import identifier_objects.VARIABLE;

public class VariableDeclarationAST extends StatementAST {

  private final TYPE type;
  private final String varname;
  private VARIABLE varObj;

  public VariableDeclarationAST(TYPE type, String varname) {
    this.type = type;
    this.varname = varname;
  }
}
