package wacc.middleware.ast_nodes.pointers_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.POINTER;
import wacc.frontend.identifier_objects.STACK_OBJECT;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class PointerElemAST extends ExpressionAST {

  private final int level;
  private final String pointerName;
  private boolean isLHS = false;

  private TYPE typeObj;
  private SymbolTable scopeST;

  public PointerElemAST(List<WaccError> errors, ParserRuleContext ctx, int level,
      String pointerName) {
    super(errors, ctx);
    this.level = level;
    this.pointerName = pointerName;
  }

  public void check() {
    scopeST = ST;

    IDENTIFIER obj = ST.lookupAll(pointerName);

    if (obj == null) {
      addError(new Undefined(ctx));
      return;
    }

    //check if obj is a stack object
    if (!(obj instanceof STACK_OBJECT)) {
      addError(new MismatchedTypes(ctx, obj, new POINTER(new TYPE())));
      return;
    }

    //check if object has pointer type
    if (!(((STACK_OBJECT) obj).getType() instanceof POINTER)) {
      addError(new MismatchedTypes(ctx, obj, new POINTER(new TYPE())));
      return;
    }

    POINTER pointer = (POINTER) ((STACK_OBJECT) obj).getType();

    TYPE type = pointer.getType();

    //in case we have multiple levels of a pointer eg: **p, we need to extract the base type
    for (int i = 0; i < level - 1; i++) {
      if (type instanceof POINTER) {
        type = ((POINTER) type).getType();
      } else {
        addError(new MismatchedTypes(ctx, type, new POINTER(new TYPE())));
        return;
      }
    }

    typeObj = type;

  }

  public TYPE getType() {
    return typeObj;
  }

  public int getLevel() {
    return level;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }

  public String getPointerName() {
    return pointerName;
  }

  public void setLHS() {
    this.isLHS = true;
  }

  public boolean isLHS() {
    return isLHS;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
