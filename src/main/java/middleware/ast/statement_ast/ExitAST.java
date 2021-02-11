package middleware.ast.statement_ast;

import errors.semantic_errors.GlobalScope;
import errors.semantic_errors.MismatchedTypes;
import identifier_objects.basic_types.INT;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class ExitAST extends StatementAST {
  private ExpressionAST expressionAST;

  public ExitAST(Token token, ExpressionAST expressionAST) {
    super(token);
    this.expressionAST = expressionAST;
  }

  @Override
  public void check() {
    expressionAST.check();
    if (!(expressionAST.getType() instanceof INT))
      addError(new MismatchedTypes(expressionAST.token, expressionAST.getType(), new INT()));
  }
}
