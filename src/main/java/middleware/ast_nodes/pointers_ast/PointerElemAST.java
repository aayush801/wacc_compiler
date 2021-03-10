package middleware.ast_nodes.pointers_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.POINTER;
import frontend.identifier_objects.STACK_OBJECT;
import frontend.identifier_objects.TYPE;
import middleware.NodeAST;
import middleware.NodeASTVisitor;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class PointerElemAST extends NodeAST {

  private final int level;
  private final String pointerName;
  private TYPE typeObj;
  private SymbolTable scopeST;

  public PointerElemAST(ParserRuleContext ctx, int level, String pointerName) {
    super(ctx);
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

    if (!(obj instanceof STACK_OBJECT)) {
      addError(new MismatchedTypes(ctx, obj, new POINTER(new TYPE())));
      return;
    }

    if (!(((STACK_OBJECT) obj).getType() instanceof POINTER)) {
      addError(new MismatchedTypes(ctx, obj, new POINTER(new TYPE())));
      return;
    }

    POINTER pointer = (POINTER) ((STACK_OBJECT) obj).getType();

    TYPE type = pointer.getType();

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

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return null;
  }
}
