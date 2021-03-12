package wacc.middleware.ast_nodes.statement_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class BeginAST extends StatementAST {

  private final StatementAST statementAST;
  private SymbolTable scopeST;

  public BeginAST(List<WaccError> errors, ParserRuleContext ctx, StatementAST statementAST) {
    super(errors, ctx);
    this.statementAST = statementAST;
  }

  @Override
  public void check() {
    // Create new symbol table(scope) for the statement.
    scopeST = ST = new SymbolTable(ST);

    // Check that the statement inside the begin block is valid.
    statementAST.check();

    // Reset the symbol table (i.e. return to the old scope).
    ST = ST.getEncSymTable();
  }

  public StatementAST getStatementAST() {
    return statementAST;
  }

  public SymbolTable getScope() {
    return scopeST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
