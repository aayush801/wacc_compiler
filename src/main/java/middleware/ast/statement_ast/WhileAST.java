package middleware.ast.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.basic_types.BOOL;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;
import symbol_table.SymbolTable;

public class WhileAST extends StatementAST {
  private ExpressionAST expressionAST;
  private StatementAST statementAST;

  public WhileAST(Token token, ExpressionAST expressionAST, StatementAST statementAST) {
    super(token);
    this.expressionAST = expressionAST;
    this.statementAST = statementAST;
  }

  @Override
  public void check() {
    expressionAST.check();
    if (!(expressionAST.getType() instanceof BOOL))
      addError(new MismatchedTypes(expressionAST.token, expressionAST.getType(), new BOOL()));
    else{
      ST = new SymbolTable(ST);
      statementAST.check();
      ST = ST.getEncSymTable();
    }
  }
}
