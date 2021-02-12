package middleware.ast.pair_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.basic_types.PAIR;
import identifier_objects.TYPE;
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
    fstExpr.check();
    sndExpr.check();

    if (!(fstExpr.getType() instanceof TYPE)) {
      addError(new MismatchedTypes(fstExpr.token,
          fstExpr.getType(), new TYPE()));

    } else if (!(sndExpr.getType() instanceof TYPE)) {
      addError(new MismatchedTypes(sndExpr.token,
          sndExpr.getType(), new TYPE()));

    } else {
      pair = new PAIR((TYPE) fstExpr.getType(), (TYPE) sndExpr.getType());
    }
  }
}
