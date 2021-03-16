package wacc.middleware.ast_nodes.types_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.CLASS;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.TypeAST;

public class ClassTypeAST extends TypeAST {

  private final String className;
  private CLASS classObj;

  public ClassTypeAST(List<WaccError> errors,
      ParserRuleContext ctx, String className) {
    super(errors, ctx);
    this.className = className;
  }

  @Override
  public void check() {
    IDENTIFIER obj = ST.lookupAll(className);

    if (obj == null) {
      addError(new Undefined(ctx));
      return;
    }

    if (!(obj instanceof CLASS)) {
      addError(new MismatchedTypes(ctx, obj, new CLASS(className, ST)));
      return;
    }

    classObj = (CLASS) obj;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public TYPE getType() {
    return classObj;
  }
}
