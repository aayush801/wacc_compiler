package middleware.function_ast;

import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Move;
import backend.registers.Register;
import errors.semantic_errors.InvalidArguments;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.FUNCTION;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import java.util.ArrayList;
import java.util.List;
import middleware.ExpressionAST;
import middleware.NodeAST;
import middleware.NodeASTList;
import org.antlr.v4.runtime.ParserRuleContext;

public class FunctionCallAST extends NodeAST {

  private final String funcName;
  private final NodeASTList<ExpressionAST> actuals;
  private FUNCTION funcObj;

  public FunctionCallAST(ParserRuleContext ctx, String funcName,
      NodeASTList<ExpressionAST> actuals) {
    super(ctx);
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
  public List<Instruction> translate(List<Register> registers) {
    Register dest = registers.get(0);
    List<Instruction> instructions = new ArrayList<>();
    for (ExpressionAST expr : actuals) {
      List<Instruction> exprInstructions = expr.translate(registers);
      instructions.addAll(exprInstructions);

    }
    // branch to the function label
    instructions.add(new Branch("f_" + funcName, true));

    // store the result in the destination register
    instructions.add(new Move(dest, new Register(0)));

    return instructions;
  }

}

