package middleware.ast.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.basic_types.BOOL;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;
import symbol_table.SymbolTable;

public class IfElseAST extends StatementAST {

  private ExpressionAST expressionAST;
  private StatementAST firstStatAST, secondStatAST;

  public IfElseAST(Token token, ExpressionAST expressionAST,
      StatementAST firstStatAST, StatementAST secondStatAST) {
    super(token);
    this.expressionAST = expressionAST;
    this.firstStatAST = firstStatAST;
    this.secondStatAST = secondStatAST;
  }

  @Override
  public void check() {
    expressionAST.check();

    if (!(expressionAST.getType() instanceof BOOL)) {
      addError(new MismatchedTypes(
          expressionAST.token, expressionAST.getType(), new BOOL())
      );
    } else {
      ST = new SymbolTable(ST);
      firstStatAST.check();
      ST = ST.getEncSymTable();

      ST = new SymbolTable(ST);
      secondStatAST.check();
      ST = ST.getEncSymTable();
    }
  }
}
