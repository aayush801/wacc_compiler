package wacc.middleware.ast_nodes.statement_ast;

import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class PrintAST extends StatementAST {

  private final ExpressionAST expr;
  // Used to differentiate between print and println.
  private final boolean newLine;
  private TYPE type;

  public PrintAST(ParserRuleContext ctx, ExpressionAST expr, boolean newLine) {
    super(ctx);
    this.expr = expr;
    this.newLine = newLine;
  }

  public ExpressionAST getExpr() {
    return expr;
  }

  public boolean isNewLine() {
    return newLine;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    expr.check();
    type = expr.getType();
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
