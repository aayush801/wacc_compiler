package middleware.ast.statement_ast;

import org.antlr.v4.runtime.Token;

public class SkipAST extends StatementAST{

  public SkipAST(Token token) {
    super(token);
  }

  @Override
  public void check() {
  }
}
