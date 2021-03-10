package middleware.ast_nodes.types_ast;

import frontend.identifier_objects.POINTER;
import frontend.identifier_objects.TYPE;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.TypeAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class PointerTypeAST extends TypeAST {

  private final int level;
  private final TypeAST typeAST;

  public POINTER pointerObj;

  public PointerTypeAST(ParserRuleContext ctx, int level,
      TypeAST baseTypeAST) {
    super(ctx);
    this.level = level;
    this.typeAST = baseTypeAST;
  }

  @Override
  public void check() {
    typeAST.check();

    pointerObj = new POINTER(typeAST.getType());

    // recursively encapsulate pointer object
    // when multiple stars are used
    for (int i = 0; i < level - 1; i++) {

      pointerObj = new POINTER(pointerObj);

    }
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
