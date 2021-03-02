package middleware.ast_nodes.types_ast;

import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.PAIR;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.TypeAST;
import org.antlr.v4.runtime.ParserRuleContext;

// A Pair Type has 2 PairElemTypes.

public class PairTypeAST extends TypeAST {

  private final PairElemTypeAST pairElemType1;
  private final PairElemTypeAST pairElemType2;
  private TYPE type;

  public PairTypeAST(ParserRuleContext ctx, PairElemTypeAST pairElemType1,
      PairElemTypeAST pairElemType2) {
    super(ctx);
    this.pairElemType1 = pairElemType1;
    this.pairElemType2 = pairElemType2;
  }

  @Override
  public void check() {
    // check both pairElemTypes.
    pairElemType1.check();
    pairElemType2.check();

    // Make a new Pair.
    type = new PAIR(pairElemType1.getType(), pairElemType2.getType());
  }

  @Override
  public TYPE getType() {
    return type;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
