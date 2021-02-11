package middleware.ast.statement_ast;

import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class PrintAST extends StatementAST {

  private final ExpressionAST expr;
  private final int newLine;

  public PrintAST(Token token, ExpressionAST expr, int newLine) {
    super(token);
    this.expr = expr;
    this.newLine = newLine;
  }

  @Override
  public void check() {
    expr.check();
  }
}
