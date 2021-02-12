package middleware.ast;

import middleware.ast.function_ast.FunctionDeclarationAST;
import middleware.ast.statement_ast.StatementAST;
import org.antlr.v4.runtime.Token;

// AST Node for the entire program.

public class ProgAST extends NodeAST {

  private final NodeASTList<FunctionDeclarationAST> functionDeclarationASTS;
  private final StatementAST statementAST;

  public ProgAST(Token token,
      NodeASTList<FunctionDeclarationAST> functionDeclarationASTS,
      StatementAST statementAST) {
    super(token);
    this.functionDeclarationASTS = functionDeclarationASTS;
    this.statementAST = statementAST;
  }

  @Override
  public void check() {

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
}
