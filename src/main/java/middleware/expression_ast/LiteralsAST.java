package middleware.expression_ast;

import backend.NodeASTVisitor;
import frontend.identifier_objects.TYPE;
import middleware.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class LiteralsAST extends ExpressionAST {

  private final TYPE type;

  public LiteralsAST(ParserRuleContext ctx, TYPE type) {
    super(ctx);
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
