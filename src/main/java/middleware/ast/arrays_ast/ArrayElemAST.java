package middleware.ast.arrays_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.PARAM;
import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import identifier_objects.basic_types.ARRAY;
import identifier_objects.basic_types.INT;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.NodeASTList;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class ArrayElemAST extends ExpressionAST {

  public TYPE type;
  private final String arrayName;
  private final NodeASTList<ExpressionAST> expressionASTS;

  public ArrayElemAST(Token token, String arrayName,
      NodeASTList<ExpressionAST> expressionASTS) {
    super(token);
    this.arrayName = arrayName;
    this.expressionASTS = expressionASTS;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {

    // lookup the array name in the symbol table.
    IDENTIFIER array = ST.lookupAll(arrayName);

    // Verify that array name is found.
    if (array == null) {
      addError(new Undefined(token, arrayName));
      return;
    }

    // If array is stored as a VARIABLE in the ST, get the real type.
    if (array instanceof VARIABLE) {
      array = ((VARIABLE) array).getType();
    }

    // If array is stored as a PARAM in the ST, get the real type.
    if (array instanceof PARAM) {
      array = ((PARAM) array).getType();
    }

    // Verify that array is an ARRAY at this point.
    if (!(array instanceof ARRAY)) {
      addError(new MismatchedTypes(token, array, new ARRAY(new EXPR())));
      return;
    }

    // Verify that any given expressions are INTs.
    for (ExpressionAST expressionAST : expressionASTS) {
      expressionAST.check();
      if (!(expressionAST.getType() instanceof INT)) {
        addError(
            new MismatchedTypes(
                expressionAST.token,
                expressionAST.getType(),
                new INT())
        );
      }
    }

    // Finally, update the type.
    type = ((ARRAY) array).getType();

  }
}
