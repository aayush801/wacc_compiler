package wacc.middleware.ast_nodes.expression_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.basic_types.INT;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.TypeAST;

public class SizeOfAST extends ExpressionAST {

  private TypeAST typeAST;
  private ExpressionAST expressionAST;
  private int size;

  public SizeOfAST(List<WaccError> errors, ParserRuleContext ctx,
      TypeAST typeAST) {
    super(errors, ctx);
    this.typeAST = typeAST;
  }


  public SizeOfAST(List<WaccError> errors, ParserRuleContext ctx,
      ExpressionAST expressionAST) {
    super(errors, ctx);
    this.expressionAST = expressionAST;
  }

  @Override
  public void check() {
    TYPE typeObj = null;

    if (typeAST != null) {
      typeAST.check();
      typeObj = typeAST.getType();
    }

    if (expressionAST != null) {
      expressionAST.check();
      typeObj = expressionAST.getType();
    }

    // type is null only if error was encountered elsewhere
    if (typeObj == null) {
      return;
    }

    // save the size of the type
    size = typeObj.getSize();

    // the return type is always integer
    type = new INT();
  }

  public int getSize() {
    return size;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
