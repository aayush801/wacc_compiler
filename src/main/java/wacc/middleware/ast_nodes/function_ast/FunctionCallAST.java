package wacc.middleware.ast_nodes.function_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.InvalidArguments;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.errors.semantic_errors.NotAFunction;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.FUNCTION;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.basic_types.VOID;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.symbol_table.SymbolTable;

public class FunctionCallAST extends StatementAST implements FunctionCallInterface {

  private final String baseName;
  private String funcName;
  private final NodeASTList<ExpressionAST> actuals;
  private FUNCTION funcObj;
  private TYPE lhsReturnType;

  public FunctionCallAST(List<WaccError> errors, ParserRuleContext ctx,
      String funcName, NodeASTList<ExpressionAST> actuals) {
    super(errors, ctx);
    baseName = this.funcName = funcName;
    this.actuals = actuals;
  }

  public NodeASTList<ExpressionAST> getActuals() {
    return actuals;
  }

  public String getBaseName() {
    return baseName;
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

    //get list of indexes for functions with the same basename
    List<Integer> indices = SymbolTable.funcIndices.get(baseName);

    if (indices == null) {
      addError(new Undefined(ctx, baseName));
      return;
    }

    Integer last = indices.get(indices.size() - 1);
    IDENTIFIER function;

    for (Integer index : indices) {
      String tempFuncName = baseName + index;
      // look for the function object in the symbol table
      function = ST.lookupAll(tempFuncName);
      if (function == null) {

        // if the function is undefined within the current scope
        addError(new Undefined(ctx, baseName));

      }
      else if (!(function instanceof FUNCTION)) {

        // if the funcName does NOT actually refer to a function
        addError(new MismatchedTypes(ctx, function, new FUNCTION(new TYPE())));

      }
      else if (actuals.size() != ((FUNCTION) function).formals.size()) {

        // if the parameter size does not match up with the number of parameters,
        // the actual function takes, then throw invalid argument exception
        if (index.equals(last)) {
          addError(
              new InvalidArguments(ctx, baseName, ((FUNCTION) function).formals.size(),
                  actuals.size()));
        }

      }
      else {

        //check if function is compatible with lhs type or void
        if (((FUNCTION) function).getReturnType() instanceof VOID
           || isCompatible(lhsReturnType, ((FUNCTION) function).getReturnType())) {

          // found matching function
          boolean foundMatch = true;

          // go through each parameter and check if the types
          // of the callee match up with the caller
          for (int i = 0; i < actuals.size(); i++) {

            actuals.get(i).check();

            IDENTIFIER actualType = actuals.get(i).getType();
            IDENTIFIER formalType = ((FUNCTION) function).formals.get(i).getType();

            // check compatibility
            if (!(isCompatible(actualType, formalType))) {
              foundMatch = false;
              if (index.equals(last)) {
                addError(new MismatchedTypes(actuals.get(i).ctx, actualType, formalType));
              } else {
                break;
              }
            }

          }

          if (foundMatch) {
            // save the function obj in the ast node
            funcObj = (FUNCTION) function;
            funcName = funcObj.getName();
            return;
          }

        }
      }
    }

    //if reached here, function has not been found therefore we throw an error
    addError(new NotAFunction(ctx, baseName));

  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}

