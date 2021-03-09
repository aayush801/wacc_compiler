package middleware.ast_nodes.types_ast;

import frontend.identifier_objects.POINTER;
import frontend.identifier_objects.TYPE;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.TypeAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class PointerTypeAST extends TypeAST {

  private final int level;
  public POINTER pointerObj;
  private TypeAST typeAST;

  public PointerTypeAST(ParserRuleContext ctx, int dimensions,
      TypeAST baseTypeAST) {
    super(ctx);
    this.level = dimensions;
    this.typeAST = baseTypeAST;
  }

  @Override
  public void check() {
    typeAST.check();
    pointerObj = new POINTER(typeAST.getType());
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public TYPE getType() {
    return pointerObj;
  }
}
