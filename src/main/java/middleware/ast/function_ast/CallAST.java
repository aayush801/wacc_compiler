package middleware.ast.function_ast;

import identifier_objects.FUNCTION;
import middleware.ast.AbstractSyntaxTree;

public class CallAST extends AbstractSyntaxTree {

  private final String funcname;
  private final ExpressionASTList actuals;
  private FUNCTION funcObj;

  public CallAST(String funcname, ExpressionASTList actuals) {
    this.funcname = funcname;
    this.actuals = actuals;
  }
}
