package middleware.ast_nodes.pair_ast;

import middleware.NodeASTVisitor;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.PAIR;
import middleware.ExpressionAST;
import middleware.NodeAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class NewPairAST extends NodeAST {

  private final ExpressionAST fstExpr, sndExpr;
  private PAIR pair;

  public NewPairAST(ParserRuleContext ctx, ExpressionAST fstExpr,
      ExpressionAST sndExpr) {
    super(ctx);
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
