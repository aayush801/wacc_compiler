package wacc.middleware;


import java.util.List;
import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.TYPE;
import org.antlr.v4.runtime.ParserRuleContext;


public abstract class ExpressionAST extends NodeAST {

  protected TYPE type;

  public ExpressionAST(List<WaccError> errors, ParserRuleContext ctx) {
    super(errors, ctx);
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

