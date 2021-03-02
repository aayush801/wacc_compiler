package middleware.ast_nodes.types_ast;

import errors.semantic_errors.Undefined;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.PAIR;
import middleware.NodeAST;
import middleware.NodeASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

// PairElemType could be a baseType, an arrayType, or an identifier.
// 3 constructors handle each case.

public class PairElemTypeAST extends NodeAST {

  private BaseTypeAST baseTypeAST;
  private ArrayTypeAST arrayTypeAST;
  private String pairName;
  private TYPE type;

  public PairElemTypeAST(ParserRuleContext ctx, BaseTypeAST baseTypeAST) {
    super(ctx);
    this.baseTypeAST = baseTypeAST;
  }

  public PairElemTypeAST(ParserRuleContext ctx, ArrayTypeAST arrayTypeAST) {
    super(ctx);
    this.arrayTypeAST = arrayTypeAST;
  }

  public PairElemTypeAST(ParserRuleContext ctx, String pairname) {
    super(ctx);
    this.pairName = pairname;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    // Check what this PairElemType actually is, and set type accordingly.

    if (pairName != null) {
      type = new PAIR();
      return;
    }

    if (baseTypeAST != null) {
      baseTypeAST.check();
      if (baseTypeAST.getType() == null) {
        addError(new Undefined(ctx));
        return;
      }
      type = baseTypeAST.getType();
      return;
    }

    if (arrayTypeAST != null) {
      arrayTypeAST.check();
      if (arrayTypeAST.getType() == null) {
        addError(new Undefined(ctx));
        return;
      }
      type = arrayTypeAST.getType();
    }
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
