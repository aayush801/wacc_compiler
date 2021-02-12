package middleware.ast.statement_ast;

import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class PrintAST extends StatementAST {

  private final ExpressionAST expr;

  // Used to differentiate between print and println.
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

  // If newLine is 1, it means we want to print on a newLine
  // To be used during code generation.
  public boolean newLine() {
    return newLine == 1;
  }
}
