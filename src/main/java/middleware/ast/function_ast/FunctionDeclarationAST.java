package middleware.ast.function_ast;

import identifier_objects.FUNCTION;
import identifier_objects.TYPE;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

public class FunctionDeclarationAST extends NodeAST {

  private final TYPE returnType;
  private final String funcname;
  private final ParamASTList parameters;
  private FUNCTION funcObj;

  public FunctionDeclarationAST(Token token, TYPE returnType, String funcname,
      ParamASTList parameters) {
    super(token);
    this.returnType = returnType;
    this.funcname = funcname;
    this.parameters = parameters;
  }
}
