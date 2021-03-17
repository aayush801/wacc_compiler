package wacc.middleware.ast_nodes.class_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.NotAMethod;
import wacc.errors.semantic_errors.Undefined;
import wacc.errors.semantic_errors.VisibilityError;
import wacc.frontend.identifier_objects.CLASS;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.METHOD;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.Visibility;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.function_ast.FunctionCallAST;
import wacc.middleware.ast_nodes.function_ast.FunctionCallInterface;
import wacc.middleware.symbol_table.SymbolTable;

public class MethodCallAST extends FunctionCallAST implements FunctionCallInterface {

  private final ObjectFieldAST objectFieldAST;
  private SymbolTable scopeST;

  private CLASS classObj;

  public MethodCallAST(List<WaccError> errors, ParserRuleContext ctx,
      ObjectFieldAST objectFieldAST, NodeASTList<ExpressionAST> actuals) {
    super(errors, ctx, objectFieldAST.getIdentifier(), actuals);
    this.objectFieldAST = objectFieldAST;
  }

  @Override
  public void check() {
    scopeST = ST;

    objectFieldAST.check();

    classObj = objectFieldAST.getClassObj();

    List<Integer> lst = SymbolTable.funcIndices.get(getBaseName());

    if (lst == null) {
      addError(new Undefined(ctx, getBaseName()));
      return;
    }
    SymbolTable classScope = classObj.getScopeST();
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

    super.check();

    ST = scopeST;

  }

  public ObjectFieldAST getObjectFieldAST() {
    return objectFieldAST;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
