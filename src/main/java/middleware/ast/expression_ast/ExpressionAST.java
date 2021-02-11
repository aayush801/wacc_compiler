package middleware.ast.expression_ast;

import identifier_objects.TYPE;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

public class ExpressionAST extends NodeAST {
  public TYPE type;

  public ExpressionAST(Token token) {
    super(token);
  }

}
