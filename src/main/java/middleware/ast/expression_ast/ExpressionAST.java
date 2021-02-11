package middleware.ast.expression_ast;

import middleware.ast.AbstractSyntaxTree;

import identifier_objects.IDENTIFIER;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;


public class ExpressionAST extends NodeAST {

  protected IDENTIFIER type;

  public ExpressionAST(Token token) {
    super(token);
  }

  public IDENTIFIER getType() {
    return type;
  }

  public boolean isIdent() { return false; }

}
