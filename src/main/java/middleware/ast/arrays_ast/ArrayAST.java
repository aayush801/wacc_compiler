package middleware.ast.arrays_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.basic_types.ARRAY;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.NodeAST;
import middleware.ast.NodeASTList;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class ArrayAST extends NodeAST {

  public ARRAY arrayObj;
  private NodeASTList<ExpressionAST> expressionASTList;

  public ArrayAST(Token token, NodeASTList<ExpressionAST> expressionASTList) {
    super(token);
    this.expressionASTList = expressionASTList;
  }

  @Override
  public void check() {
    if (expressionASTList.isEmpty()) {
      arrayObj = new ARRAY(new EXPR());
    } else {
      expressionASTList.get(0).check();
      IDENTIFIER curType = expressionASTList.get(0).getType();
      if (!(curType instanceof TYPE)) {
        addError(
            new MismatchedTypes(
                expressionASTList.get(0).token, expressionASTList.get(0).getType(), new EXPR()));
      } else {
        for (ExpressionAST expressionAST : expressionASTList) {
          expressionAST.check();
          if (!(isCompatible(expressionAST.getType(), curType)))
            addError(new MismatchedTypes(expressionAST.token, expressionAST.getType(), curType));
        }
        arrayObj = new ARRAY((TYPE) curType);
      }
    }
  }
}
