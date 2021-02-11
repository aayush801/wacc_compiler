package middleware.ast.function_ast;

import errors.semantic_errors.InvalidArguments;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.FUNCTION;
import identifier_objects.IDENTIFIER;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.NodeAST;
import middleware.ast.NodeASTList;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class FunctionCallAST extends NodeAST {

  private final String funcName;
  private final NodeASTList<ExpressionAST> actuals;
  public FUNCTION funcObj;

  public FunctionCallAST(Token token, String funcName,
      NodeASTList<ExpressionAST> actuals) {
    super(token);
    this.funcName = funcName;
    this.actuals = actuals;
  }

  public FUNCTION getFuncObj() {
    return funcObj;
  }

  @Override
  public void check() {
    IDENTIFIER function = ST.lookupAll(funcName);

    if (function == null) {
      addError(new Undefined(token));

    } else if (!(function instanceof FUNCTION)) {
      addError(
          new MismatchedTypes(
              token, function, new FUNCTION(new EXPR())
          )
      );

    } else if (actuals.size() != ((FUNCTION) function).formals.size()) {
      addError(
          new InvalidArguments(
              token, funcName, actuals.size(),
              ((FUNCTION) function).formals.size())
      );

    } else {
      // check typing match for parameters
      for (int i = 0; i < actuals.size(); i++) {
        actuals.get(i).check();
        if (!(isCompatible(actuals.get(i).getType(),
            ((FUNCTION) function).formals.get(i).getType()))) {
          addError(
              new MismatchedTypes(token, actuals.get(i).getType(),
                  ((FUNCTION) function).formals.get(i).getType())
          );
        }
      }

      funcObj = (FUNCTION) function;
    }
  }
}
