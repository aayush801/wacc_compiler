package wacc.middleware.ast_nodes.types_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.basic_types.PAIR;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

// PairElemType could be a baseType, an arrayType, or an identifier.
// 3 constructors handle each case.

public class PairElemTypeAST extends NodeAST {

  private BaseTypeAST baseTypeAST;
  private ArrayTypeAST arrayTypeAST;
  private String pairName;
  private TYPE type;

  public PairElemTypeAST(List<WaccError> errors, ParserRuleContext ctx, BaseTypeAST baseTypeAST) {
    super(errors, ctx);
    this.baseTypeAST = baseTypeAST;
  }

  public PairElemTypeAST(List<WaccError> errors, ParserRuleContext ctx, ArrayTypeAST arrayTypeAST) {
    super(errors, ctx);
    this.arrayTypeAST = arrayTypeAST;
  }

  public PairElemTypeAST(List<WaccError> errors, ParserRuleContext ctx, String pairname) {
    super(errors, ctx);
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
