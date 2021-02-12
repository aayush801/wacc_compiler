package middleware.ast.function_ast;

import errors.semantic_errors.InvalidArguments;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.FUNCTION;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import middleware.ast.NodeAST;
import middleware.ast.NodeASTList;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class FunctionCallAST extends NodeAST {

  private final String funcName;
  private final NodeASTList<ExpressionAST> actuals;
  private FUNCTION funcObj;

  public FunctionCallAST(Token token, String funcName, NodeASTList<ExpressionAST> actuals) {
    super(token);
    this.funcName = funcName;
    this.actuals = actuals;
  }

  public FUNCTION getFuncObj() {
    return funcObj;
  }

  @Override
  public void check() {

    // look for the function object in the symbol table
    IDENTIFIER function = ST.lookupAll(funcName);

    if (function == null) {

      // if the function is undefined within the current scope
      addError(new Undefined(token));

    } else if (!(function instanceof FUNCTION)) {

      // if the funcName deos NOT actually refer to a function
      addError(new MismatchedTypes(token, function, new FUNCTION(new TYPE())));

    } else if (actuals.size() != ((FUNCTION) function).formals.size()) {

      // if the parameter size does not match up with the number of parameters,
      // the actual function takes, then throw invalid argument exception
      addError(new InvalidArguments(token, funcName, actuals.size(),
          ((FUNCTION) function).formals.size()));

    } else {

      // go through each parameter and check if the types
      // of the callee match up with the caller
      for (int i = 0; i < actuals.size(); i++) {

        actuals.get(i).check();

        IDENTIFIER actualType = actuals.get(i).getType();
        IDENTIFIER formalType = ((FUNCTION) function).formals.get(i).getType();

        // check compatibility
        if (!(isCompatible(actualType, formalType))) {
          addError(new MismatchedTypes(actuals.get(i).token, actualType, formalType));
        }

      }

      // save the function obj in the ast node
      funcObj = (FUNCTION) function;

    }

  }

}

