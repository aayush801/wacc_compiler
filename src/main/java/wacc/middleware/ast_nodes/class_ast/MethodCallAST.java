package wacc.middleware.ast_nodes.class_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.errors.semantic_errors.NotAMethod;
import wacc.errors.semantic_errors.Undefined;
import wacc.errors.semantic_errors.VisibilityError;
import wacc.frontend.identifier_objects.CLASS;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.METHOD;
import wacc.frontend.identifier_objects.STACK_OBJECT;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.Visibility;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.function_ast.FunctionCallAST;
import wacc.middleware.ast_nodes.function_ast.FunctionCallInterface;
import wacc.middleware.symbol_table.SymbolTable;

public class MethodCallAST extends FunctionCallAST implements FunctionCallInterface {

  private final String objectName;
  private SymbolTable scopeST;

  public MethodCallAST(List<WaccError> errors, ParserRuleContext ctx,
      String objectName, String funcName, NodeASTList<ExpressionAST> actuals) {
    super(errors, ctx, funcName, actuals);
    this.objectName = objectName;
  }

  @Override
  public void check() {
    scopeST = ST;


    IDENTIFIER obj = ST.lookup(objectName);

    if(obj == null){
      addError(new Undefined(ctx, objectName));
      return;
    }

    if(!(obj instanceof STACK_OBJECT)){
      addError(new Undefined(ctx, objectName));
      return;
    }

    if(!(((STACK_OBJECT) obj).getType() instanceof CLASS)){
      addError(new MismatchedTypes(ctx, obj, new CLASS("", ST)));
      return;
    }

    CLASS classObj = (CLASS) ((STACK_OBJECT) obj).getType();

    //get list of indexes for functions with the same basename
    List<Integer> lst = SymbolTable.funcIndices.get(getBaseName());

    if (lst == null) {
      addError(new Undefined(ctx, getBaseName()));
      return;
    }

    SymbolTable classScope = classObj.getScopeST();

    //extracting the first method with the basename called (method overloading not implemented)
    IDENTIFIER funcObj = classScope.lookup(getBaseName() + lst.get(0));

    if (!(funcObj instanceof METHOD)) {
      addError(new NotAMethod(ctx));
      return;
    }

    // if the function is private, then it should not be accessible to
    // anything calling it outside of it's class scope
    Visibility visibility = ((METHOD) funcObj).getVisibility();
    if (visibility == Visibility.PRIVATE && !ST.inScope(classScope)) {
      addError(new VisibilityError(ctx, getBaseName()));
      return;
    }

    ST = classScope;

    //calling functionCallAST check
    super.check();

    ST = scopeST;


  }

  public String getObjectName() {
    return objectName;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
