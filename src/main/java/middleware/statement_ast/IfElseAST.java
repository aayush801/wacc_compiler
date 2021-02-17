package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.BOOL;
import java.util.List;
import middleware.expression_ast.ExpressionAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.Token;

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
    IDENTIFIER type = expressionAST.getType();

    if (type == null) {
      return;
    }

    // verify that the condition is a boolean.
    if (!(type instanceof BOOL)) {
      addError(new MismatchedTypes(
          expressionAST.token, expressionAST.getType(), new BOOL())
      );
      return;
    }

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

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return null;
  }
}
