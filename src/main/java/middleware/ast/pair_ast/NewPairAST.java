package middleware.ast.pair_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.TYPE;
import identifier_objects.basic_types.PAIR;
import middleware.ast.NodeAST;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class NewPairAST extends NodeAST {

  private final ExpressionAST fstExpr, sndExpr;
  public PAIR pair;

  public NewPairAST(Token token, ExpressionAST fstExpr,
      ExpressionAST sndExpr) {
    super(token);
    this.fstExpr = fstExpr;
    this.sndExpr = sndExpr;
  }

  public PAIR getPair() {
    return pair;
  }

  @Override
  public void check() {

    // check both expressions.
    fstExpr.check();
    sndExpr.check();

    boolean error = false;

    // check that boh expressions are of type TYPE.
    // If not, they are a function identifier, which is invalid.
    if (!(fstExpr.getType() instanceof TYPE)) {
      addError(new MismatchedTypes(fstExpr.token, fstExpr.getType(), new TYPE()));
      error = true;
    }

    if (!(sndExpr.getType() instanceof TYPE)) {
      addError(new MismatchedTypes(sndExpr.token, sndExpr.getType(), new TYPE()));
        error = true;
    }

    if (!error) {
      // If both types valid, make a new pair.
      pair = new PAIR((TYPE) fstExpr.getType(), (TYPE) sndExpr.getType());
    }
  }
}
