package wacc.middleware.ast_nodes.function_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
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
import wacc.middleware.symbol_table.SymbolTable;

public class FunctionCallAST extends NodeAST implements FunctionCallInterface {

  private String funcName;
  private final NodeASTList<ExpressionAST> actuals;
  private FUNCTION funcObj;
  private TYPE lhsReturnType;

  public FunctionCallAST(List<WaccError> errors, ParserRuleContext ctx,
      String funcName, NodeASTList<ExpressionAST> actuals) {
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
  public void setLhsReturnType(TYPE lhsReturnType) {
    this.lhsReturnType = lhsReturnType;
  }

  @Override
  public void check() {

    List<Integer> indices = SymbolTable.funcIndices.get(funcName);
    Integer last = indices.get(indices.size() - 1);
    IDENTIFIER function = null;
    for (Integer index : indices) {
      String tempFuncName = funcName + index;
      // look for the function object in the symbol table
      function = ST.lookupAll(tempFuncName);
      if (function == null) {

        // if the function is undefined within the current scope
        addError(new Undefined(ctx, funcName));

      } else if (!(function instanceof FUNCTION)) {

        // if the funcName does NOT actually refer to a function
        addError(new MismatchedTypes(ctx, function, new FUNCTION(new TYPE())));

      }
      else if (actuals.size() != ((FUNCTION) function).formals.size()) {

        // if the parameter size does not match up with the number of parameters,
        // the actual function takes, then throw invalid argument exception
        if (index.equals(last)) {
          addError(
                  new InvalidArguments(ctx, funcName, ((FUNCTION) function).formals.size(),
                          actuals.size()));
        }

      }
      else {
        // go through each parameter and check if the types
        // of the callee match up with the caller
        for (int i = 0; i < actuals.size(); i++) {

          actuals.get(i).check();

          IDENTIFIER actualType = actuals.get(i).getType();
          IDENTIFIER formalType = ((FUNCTION) function).formals.get(i).getType();

          // check compatibility
          if (!(isCompatible(actualType, formalType))) {
            if (index.equals(last)) {
              addError(new MismatchedTypes(actuals.get(i).ctx, actualType, formalType));
            } else {
              break;
            }
          }
        }
        if(isCompatible(lhsReturnType, ((FUNCTION) function).getReturnType())){
          break;
        }
        if (index.equals(last)) {
          break;
        }
      }
    }
    // save the function obj in the ast node
    funcObj = (FUNCTION) function;
    funcName = funcObj.getName();
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}

