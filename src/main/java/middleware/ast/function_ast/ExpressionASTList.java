package middleware.ast.function_ast;

import middleware.ast.expression_ast.ExpressionAST;

public class ExpressionASTList {

  private final ExpressionAST[] expressions;

  public ExpressionASTList(ExpressionAST[] expressions) {
    this.expressions = expressions;
  }
}
