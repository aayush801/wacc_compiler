package middleware.ast.statement_ast;

import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

public abstract class StatementAST extends NodeAST {

  // superclass of all statement AST classes.

  public StatementAST(Token token) {
    super(token);
  }

}
