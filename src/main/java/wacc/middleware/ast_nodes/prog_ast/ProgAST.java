package wacc.middleware.ast_nodes.prog_ast;

import org.antlr.v4.runtime.ParserRuleContext;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.SymbolTable;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import wacc.middleware.ast_nodes.import_ast.ImportAST;
import wacc.middleware.ast_nodes.statement_ast.ChainedStatementAST;

// AST Node for the entire program.

public class ProgAST extends NodeAST {

  private final NodeASTList<FunctionDeclarationAST> functionDeclarationASTS;
  private StatementAST statementAST;
  private SymbolTable scopeST;
  private NodeASTList<ImportAST> importASTS;

  public ProgAST(
      ParserRuleContext ctx,
      NodeASTList<ImportAST> importASTS,
      NodeASTList<FunctionDeclarationAST> functionDeclarationASTS,
      StatementAST statementAST) {
    super(ctx);
    this.functionDeclarationASTS = functionDeclarationASTS;
    this.statementAST = statementAST;
    this.importASTS = importASTS;
  }

  @Override
  public void check() {
    scopeST = ST = SymbolTable.TopSymbolTable();

    // Go through all imported statements and record them in the top symbol table
    for (ImportAST importAST : importASTS) {
      // do semantic checks to make sure import is valid
      importAST.check();

      ProgAST importedProg = importAST.getProgAST();

      if (importedProg != null) {
        // add the import functions to funcDeclrList
        functionDeclarationASTS.addAll(importedProg.getFunctionDeclarationASTS());

        // append the statement bodies together using a chained statement
        statementAST =
            new ChainedStatementAST(statementAST.ctx, statementAST, importedProg.getStatementAST());
      }
    }

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
