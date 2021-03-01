package middleware.types_ast;

import backend.NodeASTVisitor;
import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import java.util.List;
import middleware.NodeAST;
import middleware.TypeAST;
import org.antlr.v4.runtime.ParserRuleContext;

// A basic type, which just has the type and the name of the identifier.

public class BaseTypeAST extends TypeAST {

  String typeName;
  private TYPE type;

  public BaseTypeAST(ParserRuleContext ctx, String typename) {
    super(ctx);
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
      addError(new Undefined(ctx));
    } else if (!(identifier instanceof TYPE)) {
      addError(new MismatchedTypes(ctx, identifier, new TYPE()));
    } else {
      type = (TYPE) identifier;
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return null;
  }

  @Override
  public List<Instruction> accept(NodeASTVisitor visitor) {
    return (List<Instruction>) visitor.visit(this);
  }
}
