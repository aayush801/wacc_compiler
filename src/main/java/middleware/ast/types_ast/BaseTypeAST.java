package middleware.ast.types_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import org.antlr.v4.runtime.Token;

public class BaseTypeAST extends TypeAST {

  private TYPE type;
  String typeName;

  public BaseTypeAST(Token token, String typename) {
    super(token);
    this.typeName = typename;
  }

  @Override
  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    IDENTIFIER identifier = ST.lookupAll(typeName);
    if (identifier == null) {
      addError(new Undefined(token));
    } else if (!(identifier instanceof TYPE)) {
      addError(new MismatchedTypes(token, identifier, new TYPE()));
    } else {
      type = (TYPE) identifier;
    }
  }
}
