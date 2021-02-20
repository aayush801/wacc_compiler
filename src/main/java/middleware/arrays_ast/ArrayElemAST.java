package middleware.arrays_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import frontend.identifier_objects.basic_types.ARRAY;
import frontend.identifier_objects.basic_types.INT;
import middleware.NodeAST;
import middleware.NodeASTList;
import middleware.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class ArrayElemAST extends ExpressionAST {

  private final String arrayName;
  private final NodeASTList<ExpressionAST> expressionASTS;
  public TYPE type;

  public ArrayElemAST(ParserRuleContext ctx, String arrayName,
      NodeASTList<ExpressionAST> expressionASTS) {
    super(ctx);
    this.arrayName = arrayName;
    this.expressionASTS = expressionASTS;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {

    // lookup the array name in the symbol table.
    IDENTIFIER array = NodeAST.ST.lookupAll(arrayName);

    // Verify that array name is found.
    if (array == null) {
      addError(new Undefined(ctx, arrayName));
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
      addError(new MismatchedTypes(ctx, array, new ARRAY(new TYPE())));
      return;
    }

    // Verify that any given expressions are INTs.
    for (ExpressionAST expressionAST : expressionASTS) {
      expressionAST.check();
      if (!(expressionAST.getType() instanceof INT)) {
        addError(
            new MismatchedTypes(
                expressionAST.ctx,
                expressionAST.getType(),
                new INT())
        );
      }
    }

    // Finally, update the type.
    type = ((ARRAY) array).getType();

  }
}
