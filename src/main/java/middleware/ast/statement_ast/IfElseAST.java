package middleware.ast.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.basic_types.BOOL;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;
import symbol_table.SymbolTable;

public class IfElseAST extends StatementAST {

  private final ExpressionAST expressionAST;
  private final StatementAST firstStatAST;
  private final StatementAST secondStatAST;

  public IfElseAST(Token token, ExpressionAST expressionAST,
      StatementAST firstStatAST, StatementAST secondStatAST) {
    super(token);
    this.expressionAST = expressionAST;
    this.firstStatAST = firstStatAST;
    this.secondStatAST = secondStatAST;
  }

  @Override
  public void check() {

    // Verify that the condition expression is a valid expression.
    expressionAST.check();

    // verify that the condition is a boolean.
    if (!(expressionAST.getType() instanceof BOOL)) {
      addError(new MismatchedTypes(
          expressionAST.token, expressionAST.getType(), new BOOL())
      );
    } else {

      // Create new symbol table(scope) for the 'then' statement.
      ST = new SymbolTable(ST);

      // Verify the 'then' statement.
      firstStatAST.check();

      // Reset symbol table.
      ST = ST.getEncSymTable();


      // Create new symbol table(scope) for the 'else' statement.
      ST = new SymbolTable(ST);

      // Verify the 'else' statement.
      secondStatAST.check();

      // Reset symbol table.
      ST = ST.getEncSymTable();
    }
  }
}
