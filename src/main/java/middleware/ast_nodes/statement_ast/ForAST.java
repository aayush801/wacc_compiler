package middleware.ast_nodes.statement_ast;

import middleware.ExpressionAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ForAST extends WhileAST {

  private final StatementAST initialisation, afterthought;

  public ForAST(ParserRuleContext ctx, StatementAST initialisation,
      ExpressionAST condition, StatementAST afterthought, StatementAST body) {
    super(ctx, condition, body);
    this.initialisation =  initialisation;
    this.afterthought = afterthought;
  }

  public void check() {
    initialisation.check();
    afterthought.check();
    super.check();
  }

  public StatementAST getBody() {
    return super.getStatementAST();
  }

  public StatementAST getInitialisation() {
    return initialisation;
  }

  public StatementAST getAfterthought() {
    return afterthought;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }


}
