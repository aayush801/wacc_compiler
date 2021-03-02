package middleware;

import backend.NodeASTVisitor;
import middleware.function_ast.FunctionDeclarationAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

// AST Node for the entire program.

public class ProgAST extends NodeAST {

  private final NodeASTList<FunctionDeclarationAST> functionDeclarationASTS;
  private final StatementAST statementAST;
  private SymbolTable scopeST;

  public ProgAST(ParserRuleContext ctx,
      NodeASTList<FunctionDeclarationAST> functionDeclarationASTS,
      StatementAST statementAST) {
    super(ctx);
    this.functionDeclarationASTS = functionDeclarationASTS;
    this.statementAST = statementAST;
  }

  @Override
  public void check() {
    scopeST = ST = SymbolTable.TopSymbolTable();
    // Go through any functions declared, and record them in the top symbol table.
    // This is done in a separate pass because a function body may call other functions
    // declared later on.
    for (FunctionDeclarationAST func : functionDeclarationASTS) {
      func.check();
    }

    // Now go through the actual function bodies.
    for (FunctionDeclarationAST func : functionDeclarationASTS) {
      func.checkStatement();
    }

    // Now check all the statements.
    statementAST.check();
  }


  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }


  public NodeASTList<FunctionDeclarationAST> getFunctionDeclarationASTS() {
    return functionDeclarationASTS;
  }

  public StatementAST getStatementAST() {
    return statementAST;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }

}
