package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.basic_types.BOOL;
import java.util.List;
import middleware.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;
import middleware.symbol_table.SymbolTable;

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

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return null;
  }
}
