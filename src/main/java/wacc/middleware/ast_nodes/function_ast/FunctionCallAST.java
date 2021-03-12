package wacc.middleware.ast_nodes.function_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.InvalidArguments;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.FUNCTION;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import org.antlr.v4.runtime.ParserRuleContext;

public class FunctionCallAST extends NodeAST {

  private final String funcName;
  private final NodeASTList<ExpressionAST> actuals;
  private FUNCTION funcObj;

  public FunctionCallAST(List<WaccError> errors,ParserRuleContext ctx, String funcName,
      NodeASTList<ExpressionAST> actuals) {
    super(errors, ctx);
    this.funcName = funcName;
    this.actuals = actuals;
  }

  public NodeASTList<ExpressionAST> getActuals() {
    return actuals;
  }

  public String getFuncName() {
    return funcName;
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
      addError(new Undefined(ctx, funcName));

    } else if (!(function instanceof FUNCTION)) {

      // if the funcName deos NOT actually refer to a function
      addError(new MismatchedTypes(ctx, function, new FUNCTION(new TYPE())));

    } else if (actuals.size() != ((FUNCTION) function).formals.size()) {

      // if the parameter size does not match up with the number of parameters,
      // the actual function takes, then throw invalid argument exception
      addError(new InvalidArguments(ctx, funcName, actuals.size(),
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
          addError(new MismatchedTypes(actuals.get(i).ctx, actualType, formalType));
        }
      }

      // save the function obj in the ast node
      funcObj = (FUNCTION) function;

    }

  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}

