package wacc.middleware.ast_nodes.statement_ast;

import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ForAST extends WhileAST {

  private final StatementAST initialisation;

  public ForAST(ParserRuleContext ctx, StatementAST initialisation,
      ExpressionAST condition, StatementAST afterthought, StatementAST body) {
    super(ctx, condition, new ChainedStatementAST(ctx, body, afterthought));
    this.initialisation = initialisation;
  }

  public void check() {
    initialisation.check();
    super.check();
  }

  public StatementAST getInitialisation() {
    return initialisation;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
