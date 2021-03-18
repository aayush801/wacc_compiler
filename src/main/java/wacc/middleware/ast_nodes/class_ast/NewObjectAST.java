package wacc.middleware.ast_nodes.class_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.InvalidArguments;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.CLASS;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.PARAM;
import wacc.frontend.identifier_objects.TYPE;
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

    // if class has a constructor check the params match
    if(classObj.getConstructorObj() != null) {
      List<PARAM> formals = classObj.getConstructorObj().formals;

      // check param size matches
      if (actuals.size() != formals.size()) {
        addError(new InvalidArguments(ctx, className, formals.size(), actuals.size()));
        return;
      }

      // check param types are compatible
      for (int i = 0; i < formals.size(); i++) {
        actuals.get(i).check();

        TYPE accType = actuals.get(i).getType();
        TYPE formalType = formals.get(i).getType();

        if (!(isCompatible(actuals.get(i).getType(), formalType))) {
          addError(new MismatchedTypes(ctx, accType, formalType));
          return;
        }

      }
    }

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
