package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import java.util.List;
import org.antlr.v4.runtime.Token;
import middleware.symbol_table.SymbolTable;

public class BeginAST extends StatementAST {

  private final StatementAST statementAST;

  public BeginAST(Token token, StatementAST statementAST) {
    super(token);
    this.statementAST = statementAST;
  }

  @Override
  public void check() {

    // Create new symbol table(scope) for the statement.
    ST = new SymbolTable(ST);

    // Check that the statement inside the begin block is valid.
    statementAST.check();

    // Reset the symbol table (i.e. return to the old scope).
    ST = ST.getEncSymTable();
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return null;
  }
}