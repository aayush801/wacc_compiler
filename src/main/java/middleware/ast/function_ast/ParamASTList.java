package middleware.ast.function_ast;

import identifier_objects.PARAM;

public class ParamASTList {

  private final Parameter[] params;

  public ParamASTList(Parameter[] params) {
    this.params = params;
  }

  public Parameter get(int i) {
    return params[i];
  }
}
