package middleware.ast.arrays_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.TYPE;
import identifier_objects.basic_types.ARRAY;
import identifier_objects.polymorhpic_types.EXPR;
import java.util.List;
import middleware.ast.NodeAST;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class ArrayAST extends NodeAST {

  public ARRAY arrayObj;
  private List<ExpressionAST> expressionASTList;

  public ArrayAST(Token token, List<ExpressionAST> expressionASTList) {
    super(token);
    this.expressionASTList = expressionASTList;
  }

  @Override
  public void check() {
    if (expressionASTList.isEmpty()) {
      arrayObj = new ARRAY(new EXPR());
    } else {
      expressionASTList.get(0).check();
      TYPE curType = expressionASTList.get(0).getType();
      for (ExpressionAST expressionAST : expressionASTList) {
        expressionAST.check();
        if (!(isCompatible( expressionAST.getType(), curType)))
          addError(new MismatchedTypes(expressionAST.token, expressionAST.getType(), curType));
      }
      arrayObj = new ARRAY(curType);
    }
  }
}
