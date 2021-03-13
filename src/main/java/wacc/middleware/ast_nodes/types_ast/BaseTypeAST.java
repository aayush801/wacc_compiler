package wacc.middleware.ast_nodes.types_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.TypeAST;
import org.antlr.v4.runtime.ParserRuleContext;

// A basic type, which just has the type and the name of the identifier.

public class BaseTypeAST extends TypeAST {

  String typeName;
  private TYPE type;

  public BaseTypeAST(List<WaccError> errors, ParserRuleContext ctx,
      String typename) {
    super(errors, ctx);
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
