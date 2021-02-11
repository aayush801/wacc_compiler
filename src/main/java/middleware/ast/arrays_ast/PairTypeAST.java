package middleware.ast.arrays_ast;

import identifier_objects.TYPE;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

public class PairTypeAST extends NodeAST {
  public TYPE type;
  public PairTypeAST(Token token) {
    super(token);
  }
}
