package middleware.ast.statement_ast;

import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;
import symbol_table.SymbolTable;

public abstract class StatementAST extends NodeAST {

  public StatementAST(Token token) {
    super(token);
  }
}
