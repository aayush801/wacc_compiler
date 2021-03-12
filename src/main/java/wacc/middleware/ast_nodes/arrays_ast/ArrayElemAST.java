package wacc.middleware.ast_nodes.arrays_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.PARAM;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.VARIABLE;
import wacc.frontend.identifier_objects.basic_types.ARRAY;
import wacc.frontend.identifier_objects.basic_types.INT;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class ArrayElemAST extends ExpressionAST {


  private final String arrayName;

  private final NodeASTList<ExpressionAST> expressionASTS;
  public TYPE type;
  private SymbolTable scopeST;
  private boolean isLHS = false;

  public ArrayElemAST(List<WaccError> errors,ParserRuleContext ctx, String arrayName,
      NodeASTList<ExpressionAST> expressionASTS) {
    super(errors, ctx);
    this.arrayName = arrayName;
    this.expressionASTS = expressionASTS;
  }


  public void setLHS() {
    this.isLHS = true;
  }

  public boolean isLHS() {
    return isLHS;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }

  public String getArrayName() {
    return arrayName;
  }

  public NodeASTList<ExpressionAST> getExpressionASTS() {
    return expressionASTS;
  }


  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    scopeST = ST;

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

      // get the base element type of the array (even if nested)
      array = ((ARRAY) array).getType();

    }

    type = (TYPE) array;

  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
