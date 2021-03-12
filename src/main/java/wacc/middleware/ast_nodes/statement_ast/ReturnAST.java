package wacc.middleware.ast_nodes.statement_ast;

import wacc.errors.semantic_errors.GlobalScope;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ReturnAST extends StatementAST {

  private final ExpressionAST expressionAST;
  private TYPE type;

  public ReturnAST(ParserRuleContext ctx, ExpressionAST expressionAST) {
    super(ctx);
    this.expressionAST = expressionAST;
  }

  public ExpressionAST getExpr() {
    return expressionAST;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {

    // Verify that the provided expression is a valid expression
    expressionAST.check();

    TYPE type = expressionAST.getType();

    if (ST.getEncSymTable() == null) {

      // Trying to return from the main/global scope.
      addError(new GlobalScope(ctx));
      return;

    }

    if (type == null) {
      // error has occurred elsewhere
      return;
    }

    if (!(isCompatible(type, ST.getScopeReturnType()))) {

      // Provided return type and
      // expected return type(of the function that return is in) do not match.
      addError(
          new MismatchedTypes(
              expressionAST.ctx, type, ST.getScopeReturnType())
      );

      return;

    }

    // Valid type, so set the type of this AST node.
    this.type = type;

  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
