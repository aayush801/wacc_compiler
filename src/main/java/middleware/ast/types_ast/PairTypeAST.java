package middleware.ast.types_ast;

import identifier_objects.basic_types.PAIR;
import identifier_objects.TYPE;
import org.antlr.v4.runtime.Token;

// A Pair Type has 2 PairElemTypes.

public class PairTypeAST extends TypeAST {

  private final PairElemTypeAST pairElemType1;
  private final PairElemTypeAST pairElemType2;
  private TYPE type;

  public PairTypeAST(Token token, PairElemTypeAST pairElemType1,
      PairElemTypeAST pairElemType2) {
    super(token);
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
}
