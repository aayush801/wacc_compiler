package wacc.middleware.ast_nodes.statement_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ForAST extends WhileAST {

  private final StatementAST initialisation;

  public ForAST(List<WaccError> errors, ParserRuleContext ctx, StatementAST initialisation,
      ExpressionAST condition, StatementAST afterthought, StatementAST body) {
    super(errors, ctx, condition, new ChainedStatementAST(errors, ctx, body, afterthought));
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
