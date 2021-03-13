package wacc.middleware.ast_nodes.pair_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.basic_types.PAIR;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class PairElemAST extends NodeAST {

  private final ExpressionAST exprAST;
  private final boolean isFirstElem;
  private TYPE type;

  public PairElemAST(List<WaccError> errors, ParserRuleContext ctx,
      ExpressionAST exprAST, boolean isFirstElem) {
    super(errors, ctx);
    this.exprAST = exprAST;
    this.isFirstElem = isFirstElem;
  }

  public TYPE getType() {
    return type;
  }

  public ExpressionAST getExprAST() {
    return exprAST;
  }

  public boolean isFirstElem() {
    return isFirstElem;
  }

  @Override
  public void check() {
    // check the expression.
    exprAST.check();

    // ensure that the expressions is a PAIR.
    IDENTIFIER exprType = exprAST.getType();

    if (!(exprType instanceof PAIR)) {

      addError(
          new MismatchedTypes(ctx, exprType, new PAIR(new TYPE(), new TYPE())));

      return;

    }

    // If e got exprType must be a PAIR.
    PAIR pair = (PAIR) exprType;

    // call getFirst or getSecond based on the index.
    if (isFirstElem) {
      type = pair.getFirst();
    } else {
      type = pair.getSecond();
    }
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
