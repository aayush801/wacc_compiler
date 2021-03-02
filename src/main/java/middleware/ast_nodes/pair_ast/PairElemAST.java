package middleware.ast_nodes.pair_ast;

import middleware.NodeASTVisitor;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.PAIR;
import middleware.ExpressionAST;
import middleware.NodeAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class PairElemAST extends NodeAST {

  private final ExpressionAST exprAST;
  private final boolean isFirstElem;
  private TYPE type;

  public PairElemAST(ParserRuleContext ctx, ExpressionAST exprAST, boolean isFirstElem) {
    super(ctx);
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

      addError(new MismatchedTypes(ctx, exprType, new PAIR(new TYPE(), new TYPE())));

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
