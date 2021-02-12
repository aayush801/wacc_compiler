package middleware.ast.statement_ast;

import org.antlr.v4.runtime.Token;
import symbol_table.SymbolTable;

public class BeginAST extends StatementAST {

  private final StatementAST statementAST;

  public BeginAST(Token token, StatementAST statementAST) {
    super(token);
    this.statementAST = statementAST;
  }

  @Override
  public void check() {
    ST = new SymbolTable(ST);
    statementAST.check();
    ST = ST.getEncSymTable();
  }
}
