package middleware.ast.arrays_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.basic_types.ARRAY;
import identifier_objects.basic_types.INT;
import identifier_objects.polymorhpic_types.EXPR;
import java.util.List;
import middleware.ast.NodeAST;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class ArrayElemAST extends NodeAST {
  public ARRAY arrayObj;
  private String arrayName;
  private List<ExpressionAST> expressionASTS;

  public ArrayElemAST(Token token, String arrayName, List<ExpressionAST> expressionASTS) {
    super(token);
    this.arrayName = arrayName;
    this.expressionASTS = expressionASTS;
  }

  @Override
  public void check() {
    IDENTIFIER array = ST.lookupAll(arrayName);
    if (array == null) addError(new Undefined(token, arrayName));
    else if (!(array instanceof ARRAY))
      addError(new MismatchedTypes(token, array, new ARRAY(new EXPR())));
    else {
      for (ExpressionAST expressionAST : expressionASTS) {
        expressionAST.check();
        if (!(expressionAST.getType() instanceof INT))
          addError(new MismatchedTypes(expressionAST.token, expressionAST.getType(), new INT()));
      }
      arrayObj = (ARRAY) array;
    }
  }
}
