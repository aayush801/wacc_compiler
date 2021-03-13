package wacc.middleware.ast_nodes.statement_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ChainedStatementAST extends StatementAST {

  public final StatementAST statementAST1, statementAST2;

  public ChainedStatementAST(List<WaccError> errors, ParserRuleContext ctx,
      StatementAST statementAST1, StatementAST statementAST2) {
    super(errors, ctx);
    this.statementAST1 = statementAST1;
    this.statementAST2 = statementAST2;
  }

  @Override
  public void check() {
    // Verify that both statements are valid.
    statementAST1.check();
    statementAST2.check();
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }


}
