package middleware.ast.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.basic_types.BOOL;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;
import symbol_table.SymbolTable;

public class WhileAST extends StatementAST {

  private final ExpressionAST expressionAST;
  private final StatementAST statementAST;

  public WhileAST(Token token, ExpressionAST expressionAST,
      StatementAST statementAST) {
    super(token);
    this.expressionAST = expressionAST;
    this.statementAST = statementAST;
  }

  @Override
  public void check() {
    // check the expression
    expressionAST.check();

    // verify that the condition expression is a boolean.
    if (!(expressionAST.getType() instanceof BOOL)) {
      addError(new MismatchedTypes(
          expressionAST.token, expressionAST.getType(), new BOOL())
      );
    } else {
      // expression valid, now check the statement inside the body.
      // create a new scope(symbol table) for the statement.
      ST = new SymbolTable(ST);
      statementAST.check();
      ST = ST.getEncSymTable();
    }
  }
}
