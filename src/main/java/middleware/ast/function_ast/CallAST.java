package middleware.ast.function_ast;

import identifier_objects.FUNCTION;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

public class CallAST extends NodeAST {

  private final String funcname;
  private final ExpressionASTList actuals;
  private FUNCTION funcObj;

  public CallAST(Token token, String funcname, ExpressionASTList actuals) {
    super(token);
    this.funcname = funcname;
    this.actuals = actuals;
  }
}
