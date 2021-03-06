package wacc.middleware.ast_nodes.statement_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.arrays_ast.ArrayAST;
import wacc.middleware.ast_nodes.function_ast.FunctionCallInterface;
import wacc.middleware.ast_nodes.pair_ast.NewPairAST;
import wacc.middleware.ast_nodes.pair_ast.PairElemAST;
import wacc.middleware.ast_nodes.class_ast.NewObjectAST;
import wacc.middleware.symbol_table.SymbolTable;

public class RHSAssignAST extends StatementAST {

  private NewObjectAST newObjectAST;
  private ExpressionAST expressionAST;
  private ArrayAST arrayAST;
  private NewPairAST newPairAST;
  private PairElemAST pairElemAST;
  private FunctionCallInterface functionCallAST;
  private SymbolTable scopeST;

  private TYPE type;
  private TYPE lhsType;

  public TYPE getLhsType() {
    return lhsType;
  }

  public void setLhsType(TYPE lhsType) {
    this.lhsType = lhsType;
  }

  // RHS Assign is an expression.
  public RHSAssignAST(List<WaccError> errors, ParserRuleContext ctx,
      ExpressionAST expressionAST) {
    super(errors, ctx);
    this.expressionAST = expressionAST;
  }

  // RHS Assign is an array.
  public RHSAssignAST(List<WaccError> errors, ParserRuleContext ctx,
      ArrayAST arrayAST) {
    super(errors, ctx);
    this.arrayAST = arrayAST;
  }

  // RHS Assign is a newpair.
  public RHSAssignAST(List<WaccError> errors, ParserRuleContext ctx,
      NewPairAST newPairAST) {
    super(errors, ctx);
    this.newPairAST = newPairAST;
  }

  // RHS Assign is a pairElem.
  public RHSAssignAST(List<WaccError> errors, ParserRuleContext ctx,
      PairElemAST pairElemAST) {
    super(errors, ctx);
    this.pairElemAST = pairElemAST;
  }

  // RHS Assign is a function call.
  public RHSAssignAST(List<WaccError> errors, ParserRuleContext ctx,
      FunctionCallInterface functionCallAST) {
    super(errors, ctx);
    this.functionCallAST = functionCallAST;
  }

  // RHS Assign is a newObject.
  public RHSAssignAST(List<WaccError> errors, ParserRuleContext ctx,
      NewObjectAST newObjectAST) {
    super(errors, ctx);
    this.newObjectAST = newObjectAST;
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
      functionCallAST.setLhsReturnType(lhsType);
      functionCallAST.check();

      // verify that the function object is not null. If the function is
      // present, set type to the function's return type.
      if (functionCallAST.getFuncObj() == null) {
        return;
      }

      type = functionCallAST.getFuncObj().getReturnType();
      return;
    }

    if (newObjectAST != null) {

      //check the object ast
      newObjectAST.check();

      if (newObjectAST.getClassObj() == null) {
        return;
      }

      type = newObjectAST.getClassObj();
    }

  }

  public ExpressionAST getExpressionAST() {
    return expressionAST;
  }

  public ArrayAST getArrayAST() {
    return arrayAST;
  }

  public NewPairAST getNewPairAST() {
    return newPairAST;
  }

  public PairElemAST getPairElemAST() {
    return pairElemAST;
  }

  public FunctionCallInterface getFunctionCallAST() {
    return functionCallAST;
  }

  public NewObjectAST getNewObjectAST() {
    return newObjectAST;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}

