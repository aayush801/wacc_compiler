package middleware.ast.statement_ast;

import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

public abstract class StatementAST extends NodeAST {

  public StatementAST(Token token) {
    super(token);
  }

}
