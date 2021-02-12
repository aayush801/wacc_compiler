package middleware.ast.types_ast;

import identifier_objects.TYPE;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

// TypeAST abstract class;

public abstract class TypeAST extends NodeAST {

  public TypeAST(Token token) {
    super(token);
  }

  public TYPE getType() {
    return null;
  }
}
