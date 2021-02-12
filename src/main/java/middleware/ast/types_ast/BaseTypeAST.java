package middleware.ast.types_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import org.antlr.v4.runtime.Token;

// A basic type, which just has the type and the name of the identifier.

public class BaseTypeAST extends TypeAST {

  String typeName;
  private TYPE type;

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
    // look the typeName up in the current ST.
    IDENTIFIER identifier = ST.lookupAll(typeName);

    // verify that identifier is not null and is of type TYPE.
    if (identifier == null) {
      addError(new Undefined(token));
    } else if (!(identifier instanceof TYPE)) {
      addError(new MismatchedTypes(token, identifier, new TYPE()));
    } else {
      type = (TYPE) identifier;
    }
  }
}
