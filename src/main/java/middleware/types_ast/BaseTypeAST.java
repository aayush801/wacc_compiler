package middleware.types_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import java.util.List;
import middleware.NodeAST;
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
    IDENTIFIER identifier = NodeAST.ST.lookupAll(typeName);

    // verify that identifier is not null and is of type TYPE.
    if (identifier == null) {
      addError(new Undefined(token));
    } else if (!(identifier instanceof TYPE)) {
      addError(new MismatchedTypes(token, identifier, new TYPE()));
    } else {
      type = (TYPE) identifier;
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return null;
  }
}
