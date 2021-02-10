package middleware.ast.function_ast;

import identifier_objects.TYPE;

public class Parameter {

  private final TYPE type;
  private final String varname;

  public Parameter(TYPE type, String varname) {
    this.type = type;
    this.varname = varname;
  }
}
