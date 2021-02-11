package middleware.ast.statement_ast;

import errors.semantic_errors.GlobalScope;
import errors.semantic_errors.MismatchedTypes;
import identifier_objects.TYPE;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class ReturnAST extends StatementAST {
  public TYPE type;
  private ExpressionAST expressionAST;

  public ReturnAST(Token token, ExpressionAST expressionAST) {
    super(token);
    this.expressionAST = expressionAST;
  }

  @Override
  public void check() {
    expressionAST.check();
    if (ST.getEncSymTable() == null) addError(new GlobalScope(token));
    else if (!(isCompatible(expressionAST.type, ST.getScopeReturnType())))
      addError(
          new MismatchedTypes(expressionAST.token, expressionAST.type, ST.getScopeReturnType()));
    else type = expressionAST.type;
  }
}
