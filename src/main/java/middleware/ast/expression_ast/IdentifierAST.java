package middleware.ast.expression_ast;

import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.PARAM;
import identifier_objects.VARIABLE;
import org.antlr.v4.runtime.Token;

import javax.swing.text.StyledEditorKit;

public class IdentifierAST extends ExpressionAST {

  private IDENTIFIER type;
  private final String identifier;

  public IdentifierAST(Token token, String identifier) {
    super(token);
    this.identifier = identifier;
  }

  @Override
  public IDENTIFIER getType() {
    return type;
  }

  @Override
  public void check() {
    // find the object corresponding to the identifier in the lookup table
    IDENTIFIER obj = ST.lookupAll(identifier);

    if (obj == null) {
      addError(new Undefined(token));
      return;
    }

    if (obj instanceof VARIABLE) {
      type = ((VARIABLE) obj).getType();
      return;
    }

    if (obj instanceof PARAM) {
      type = ((PARAM) obj).getType();
      return;
    }

    type = obj;
  }

  @Override
  public boolean isIdentifier() {
    return true;
  }
}
