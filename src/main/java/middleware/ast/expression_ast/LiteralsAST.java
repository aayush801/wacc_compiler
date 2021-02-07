package middleware.ast.expression_ast;

import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import org.antlr.v4.runtime.Token;

public class LiteralsAST extends ExpressionAST {

  private final TYPE type;
  private final String value;

  public LiteralsAST(Token token, String value, TYPE type) {
    super(token);
    this.type = type;
    this.value = value;
  }

  @Override
  public IDENTIFIER getType() {
    return type;
  }

  @Override
  public void check() {

  }
}
