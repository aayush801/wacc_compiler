package middleware.ast.statement_ast;

import errors.semantic_errors.GlobalScope;
import errors.semantic_errors.MismatchedTypes;
import identifier_objects.TYPE;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class ReturnAST extends StatementAST {

  private TYPE type;
  private final ExpressionAST expressionAST;

  public ReturnAST(Token token, ExpressionAST expressionAST) {
    super(token);
    this.expressionAST = expressionAST;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {

    // Verify that the provided expression is a valid expression
    expressionAST.check();

    if (ST.getEncSymTable() == null) {
      // Trying to return from the main/global scope.
      addError(new GlobalScope(token));
    } else if (!(expressionAST.getType() instanceof TYPE)) {
      // Expr is not a TYPE so it cannot be returned.
      addError(new MismatchedTypes(expressionAST.token, expressionAST.getType(), new EXPR()));
    } else if (!(isCompatible(expressionAST.getType(), ST.getScopeReturnType()))) {
      // Provided return type and
      // expected return type(of the function that return is in) do not match.
      addError(
          new MismatchedTypes(
              expressionAST.token, expressionAST.getType(), ST.getScopeReturnType())
      );
    } else {
      // Valid type, so set the type of this AST node.
      type = (TYPE) expressionAST.getType();
    }
  }
}
