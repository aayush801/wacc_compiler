package middleware.ast.pair_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.TYPE;
import identifier_objects.basic_types.PAIR;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.NodeAST;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class NewPairAST extends NodeAST {

  private final ExpressionAST fst;
  private final ExpressionAST snd;

  public PAIR pair;

  public NewPairAST(Token token, ExpressionAST fst, ExpressionAST snd) {
    super(token);
    this.fst = fst;
    this.snd = snd;
  }

  public ExpressionAST getFst() {
    return fst;
  }

  public ExpressionAST getSnd() {
    return snd;
  }

  @Override
  public void check() {
    fst.check();
    snd.check();
    if (!(fst.getType() instanceof TYPE))
      addError(new MismatchedTypes(fst.token, fst.getType(), new EXPR()));
    else if (!(snd.getType() instanceof TYPE))
      addError(new MismatchedTypes(snd.token, snd.getType(), new EXPR()));
    else {
      pair = new PAIR((TYPE) fst.getType(), (TYPE) snd.getType());
    }
  }
}
