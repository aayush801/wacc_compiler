package middleware.ast.types_ast;

import identifier_objects.basic_types.PAIR;
import identifier_objects.TYPE;
import org.antlr.v4.runtime.Token;

public class PairTypeAST extends TypeAST {

  private PairElemTypeAST pairElemType1, pairElemType2;
  private TYPE type;

  public PairTypeAST(Token token, PairElemTypeAST pairElemType1,
      PairElemTypeAST pairElemType2) {
    super(token);
    this.pairElemType1 = pairElemType1;
    this.pairElemType2 = pairElemType2;
  }

  @Override
  public void check() {
    pairElemType1.check();
    pairElemType2.check();
    type = new PAIR(pairElemType1.getType(), pairElemType2.getType());
  }

  @Override
  public TYPE getType() {
    return type;
  }
}
