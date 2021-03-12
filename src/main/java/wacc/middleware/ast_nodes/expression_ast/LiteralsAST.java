package wacc.middleware.ast_nodes.expression_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class LiteralsAST extends ExpressionAST {

  private final TYPE type;

  public LiteralsAST(List<WaccError> errors,ParserRuleContext ctx, TYPE type) {
    super(errors, ctx);
    this.type = type;
  }

  @Override
  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }


}
