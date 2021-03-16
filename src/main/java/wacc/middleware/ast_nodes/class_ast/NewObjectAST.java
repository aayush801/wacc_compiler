package wacc.middleware.ast_nodes.class_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.CLASS;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import org.antlr.v4.runtime.ParserRuleContext;

public class NewObjectAST extends NodeAST {

  private final String className;
  private final NodeASTList<ExpressionAST> actuals;

  private CLASS classObj;

  public NewObjectAST(List<WaccError> errors,ParserRuleContext ctx,
      String className, NodeASTList<ExpressionAST> actuals) {
    super(errors, ctx);
    this.className = className;
    this.actuals = actuals;
  }

  @Override
  public void check() {
    IDENTIFIER obj = ST.lookupAll(className);

    if(obj == null){
      addError(new Undefined(ctx));
      return;
    }

    if(!(obj instanceof CLASS)){
      addError(new MismatchedTypes(ctx, obj, new CLASS("", ST)));
      return;
    }

    classObj = (CLASS) obj;
  }

  public String getClassName() {
    return className;
  }

  public NodeASTList<ExpressionAST> getActuals() {
    return actuals;
  }

  public CLASS getClassObj() {
    return classObj;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
