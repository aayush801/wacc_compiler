package wacc.middleware.ast_nodes.pair_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.basic_types.PAIR;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class NewPairAST extends NodeAST {

  private final ExpressionAST fstExpr, sndExpr;
  private PAIR pair;

  public NewPairAST(List<WaccError> errors, ParserRuleContext ctx,
      ExpressionAST fstExpr, ExpressionAST sndExpr) {
    super(errors, ctx);
    this.fstExpr = fstExpr;
    this.sndExpr = sndExpr;
  }

  public PAIR getPair() {
    return pair;
  }

  public ExpressionAST getFstExpr() {
    return fstExpr;
  }

  public ExpressionAST getSndExpr() {
    return sndExpr;
  }


  @Override
  public void check() {

    // check both expressions.
    fstExpr.check();
    sndExpr.check();

    TYPE fstType = fstExpr.getType();
    TYPE sndType = sndExpr.getType();

    boolean error = false;

    // check that boh expressions are of type TYPE.
    // If not, they are a function identifier, which is invalid.
    if (fstType == null) {
      addError(
          new MismatchedTypes(fstExpr.ctx, fstType, new TYPE()));
      error = true;
    }

    if (sndType == null) {
      addError(
          new MismatchedTypes(sndExpr.ctx, sndType, new TYPE()));
      error = true;
    }

    if (!error) {
      // If both types valid, make a new pair.
      pair = new PAIR(fstType, sndType);
    }

  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
