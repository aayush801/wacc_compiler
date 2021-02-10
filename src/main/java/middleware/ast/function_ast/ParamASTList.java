package middleware.ast.function_ast;

import identifier_objects.PARAM;
import java.util.List;

public class ParamASTList {

  private final String[] paramnames;
  private final PARAM[] paramtypes;

  public ParamASTList(String[] paramnames, PARAM[] paramtypes) {
    this.paramnames = paramnames;
    this.paramtypes = paramtypes;
  }
}
