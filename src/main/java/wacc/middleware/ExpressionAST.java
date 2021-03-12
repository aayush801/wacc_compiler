package wacc.middleware;


import wacc.frontend.identifier_objects.TYPE;
import org.antlr.v4.runtime.ParserRuleContext;


public abstract class ExpressionAST extends NodeAST {

  protected TYPE type;

  public ExpressionAST(ParserRuleContext ctx) {
    super(ctx);
  }

  @Override
  public void check() {

  }

  public TYPE getType() {
    return type;
  }

  public boolean isIdentifier() {
    return false;
  }

}

