package middleware.arrays_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.ARRAY;
import java.util.List;
import middleware.NodeAST;
import middleware.NodeASTList;
import middleware.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class ArrayAST extends NodeAST {

  private final NodeASTList<ExpressionAST> expressionASTList;
  private ARRAY arrayObj;

  public ArrayAST(Token token, NodeASTList<ExpressionAST> expressionASTList) {
    super(token);
    this.expressionASTList = expressionASTList;
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
      IDENTIFIER curType = expressionASTList.get(0).getType();

      if (!(curType instanceof TYPE)) {
        addError(new MismatchedTypes(expressionASTList.get(0).token, curType, new TYPE()));

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
                    expressionAST.token,
                    expressionAST.getType(),
                    curType)
            );
          }
        }

        // create a new ARRAY object and store it.
        arrayObj = new ARRAY((TYPE) curType);

      }
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return null;
  }
}
