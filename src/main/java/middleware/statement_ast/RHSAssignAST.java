package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.addr_modes.ZeroOffset;
import backend.registers.Register;
import frontend.identifier_objects.TYPE;

import java.util.List;

import middleware.arrays_ast.ArrayAST;
import middleware.arrays_ast.ArrayElemAST;
import middleware.expression_ast.ExpressionAST;
import middleware.function_ast.FunctionCallAST;
import middleware.pair_ast.NewPairAST;
import middleware.pair_ast.PairElemAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class RHSAssignAST extends StatementAST {

  private ExpressionAST expressionAST;
  private ArrayAST arrayAST;
  private NewPairAST newPairAST;
  private PairElemAST pairElemAST;
  private FunctionCallAST functionCallAST;
  private SymbolTable scopeST;

  private TYPE type;

  // RHS Assign is an expression.
  public RHSAssignAST(ParserRuleContext ctx, ExpressionAST expressionAST) {
    super(ctx);
    this.expressionAST = expressionAST;
  }

  // RHS Assign is an array.
  public RHSAssignAST(ParserRuleContext ctx, ArrayAST arrayAST) {
    super(ctx);
    this.arrayAST = arrayAST;
  }

  // RHS Assign is a newpair.
  public RHSAssignAST(ParserRuleContext ctx, NewPairAST newPairAST) {
    super(ctx);
    this.newPairAST = newPairAST;
  }

  // RHS Assign is a pairElem.
  public RHSAssignAST(ParserRuleContext ctx, PairElemAST pairElemAST) {
    super(ctx);
    this.pairElemAST = pairElemAST;
  }

  // RHS Assign is a function call.
  public RHSAssignAST(ParserRuleContext ctx, FunctionCallAST functionCallAST) {
    super(ctx);
    this.functionCallAST = functionCallAST;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {

    scopeST = ST;

    if (expressionAST != null) {
      // case when assign RHS is an expression.

      // check the expression.
      expressionAST.check();

      if (expressionAST.getType() == null) {
        // error has occurred elsewhere
        return;
      }

      type = expressionAST.getType();

      return;

    }

    if (arrayAST != null) {
      // case when assign RHS is an array.

      // check the array.
      arrayAST.check();

      // Verify that the array is not null. If it is present, set the type.
      if (arrayAST.getArrayObj() == null) {
        // error has occurred elsewhere
        return;
      }

      type = arrayAST.getArrayObj();

      return;

    }

    if (newPairAST != null) {
      // case when assign RHS is a newpair.

      // check the newpair.
      newPairAST.check();

      // verify that the newpair is not null. If it is present, set the type.
      if (newPairAST.getPair() == null) {
        return;
      }

      type = newPairAST.getPair();

      return;

    }

    if (pairElemAST != null) {
      // case when assign RHS is a pairElem.

      // check the pairelem.
      pairElemAST.check();

      // verify that the pairelem is not null. If it is present, set the type.
      if (pairElemAST.getType() == null) {
        return;
      }

      type = pairElemAST.getType();

      return;
    }

    if (functionCallAST != null) {
      // case when assign RHS is a function call.

      // check the function call.
      functionCallAST.check();

      // verify that the function object is not null. If the function is
      // present, set type to the function's return type.
      if (functionCallAST.getFuncObj() == null) {
        return;
      }

      type = functionCallAST.getFuncObj().getReturnType();

    }

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    if (expressionAST != null) {
      Register target = registers.get(0);
      List<Instruction> ret = expressionAST.translate(registers);
      
      if (expressionAST instanceof ArrayElemAST) {
        ret.add(new Load(target, new ZeroOffset(target)));
      }

      return ret;

    }

    if (arrayAST != null) {

      return arrayAST.translate(registers);

    }

    if (newPairAST != null) {

      return newPairAST.translate(registers);

    }

    if (pairElemAST != null) {

      return pairElemAST.translate(registers);

    }

    if (functionCallAST != null) {

      return functionCallAST.translate(registers);

    }

    return null;
  }
}

