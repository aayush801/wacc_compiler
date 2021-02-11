package middleware.ast.arrays_ast;

import identifier_objects.TYPE;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

public class BaseTypeAST extends NodeAST
{
  public TYPE type;

  public BaseTypeAST(Token token) {
    super(token);
  }
}
