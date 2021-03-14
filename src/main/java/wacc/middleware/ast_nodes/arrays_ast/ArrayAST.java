package wacc.middleware.ast_nodes.arrays_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.basic_types.ARRAY;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import org.antlr.v4.runtime.ParserRuleContext;

public class ArrayAST extends ExpressionAST {


  private final NodeASTList<ExpressionAST> expressionASTList;
  private ARRAY arrayObj;

  public ArrayAST(List<WaccError> errors, ParserRuleContext ctx,
      NodeASTList<ExpressionAST> expressionASTList) {
    super(errors, ctx);
    this.expressionASTList = expressionASTList;
  }

  public NodeASTList<ExpressionAST> getExpressionASTList() {
    return expressionASTList;
  }

  public ARRAY getArrayObj() {
    return arrayObj;
  }

  @Override
  public void check() {

    // Case where an empty array is created.
    if (expressionASTList.isEmpty()) {

      arrayObj = new ARRAY(new TYPE());

    } else {
      // Array has >0 elements.

      // check the first element.
      expressionASTList.get(0).check();

      // Verify that the first element's type is a TYPE.
      TYPE curType = expressionASTList.get(0).getType();

      if (curType == null) {
        addError(new MismatchedTypes(expressionASTList.get(0).ctx, curType,
            new TYPE()));

      } else {

        // Check that all other elements of the array have the same type as
        // the first element.
        int arraySize = expressionASTList.size();
        for (int i = 1; i < arraySize; i++) {
          ExpressionAST expressionAST = expressionASTList.get(i);
          expressionAST.check();
          if (!(isCompatible(expressionAST.getType(), curType))) {
            addError(
                new MismatchedTypes(
                    expressionAST.ctx,
                    expressionAST.getType(),
                    curType)
            );
          }
        }

        // create a new ARRAY object and store it.
        arrayObj = new ARRAY(curType);
      }
    }
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
