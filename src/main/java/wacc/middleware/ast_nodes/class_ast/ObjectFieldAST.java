package wacc.middleware.ast_nodes.class_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.w3c.dom.Node;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.errors.semantic_errors.Undefined;
import wacc.errors.semantic_errors.VisibilityError;
import wacc.frontend.identifier_objects.CLASS;
import wacc.frontend.identifier_objects.FIELD;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.STACK_OBJECT;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.VARIABLE;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.Visibility;
import wacc.middleware.symbol_table.SymbolTable;

public class ObjectFieldAST extends ExpressionAST {
  private final String objectName;
  private final String identifier;

  private boolean isLHS = false;

  private CLASS classObj;
  private SymbolTable scopeST;

  public ObjectFieldAST(List<WaccError> errors, ParserRuleContext ctx, String objectName, String identifier) {
    super(errors, ctx);
    this.objectName = objectName;
    this.identifier = identifier;
  }

  @Override
  public void check() {
    scopeST = ST;

    IDENTIFIER obj = ST.lookupAll(objectName);

    if(obj == null){
      addError(new Undefined(ctx));
      return;
    }

    if (!(obj instanceof STACK_OBJECT)) {
      addError(new Undefined(ctx));
      return;
    }

    if (!(((STACK_OBJECT) obj).getType() instanceof CLASS)) {
      addError(new MismatchedTypes(ctx, obj, new CLASS("", ST)));
      return;
    }

    classObj = (CLASS) ((STACK_OBJECT) obj).getType();

    IDENTIFIER field = classObj.getScopeST().lookup(identifier);

    if(field == null){
      addError(new Undefined(ctx, identifier));
      return;
    }

    if(field instanceof FIELD){
      if(((FIELD) field).getVisibility() == Visibility.PRIVATE && !ST.inScope(classObj.getScopeST())){
        addError(new VisibilityError(ctx, identifier));
        return;
      }
      type = ((FIELD) field).getType();
    }

  }


  public void setLHS() {
    this.isLHS = true;
  }

  public boolean isLHS() {
    return isLHS;
  }

  public String getObjectName() {
    return objectName;
  }

  public CLASS getClassObj() {
    return classObj;
  }

  public String getIdentifier() {
    return identifier;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
