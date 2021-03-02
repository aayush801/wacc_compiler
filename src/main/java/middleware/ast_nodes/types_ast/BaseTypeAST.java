package middleware.ast_nodes.types_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import middleware.NodeAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.TypeAST;
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
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
