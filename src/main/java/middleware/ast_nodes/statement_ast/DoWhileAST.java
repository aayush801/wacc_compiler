package middleware.ast_nodes.statement_ast;

import middleware.ExpressionAST;
import middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class DoWhileAST extends WhileAST {

  public DoWhileAST(ParserRuleContext ctx, ExpressionAST expressionAST,
      StatementAST statementAST) {
    super(ctx, expressionAST, statementAST);
  }
}
