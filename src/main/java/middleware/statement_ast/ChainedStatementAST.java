package middleware.statement_ast;

import backend.NodeASTVisitor;
import middleware.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ChainedStatementAST extends StatementAST {

  public final StatementAST statementAST1, statementAST2;

  public ChainedStatementAST(ParserRuleContext ctx, StatementAST statementAST1,
      StatementAST statementAST2) {
    super(ctx);
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
