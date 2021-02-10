package middleware.ast.function_ast;

import identifier_objects.FUNCTION;
import identifier_objects.TYPE;
import middleware.ast.AbstractSyntaxTree;

public class FunctionDeclarationAST extends AbstractSyntaxTree {

  private final TYPE returnType;
  private final String funcname;
  private final ParamASTList parameters;
  private FUNCTION funcObj;

  public FunctionDeclarationAST(TYPE returnType, String funcname,
      ParamASTList parameters) {
    this.returnType = returnType;
    this.funcname = funcname;
    this.parameters = parameters;
  }
}
