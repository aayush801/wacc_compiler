package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import java.util.List;
import middleware.StatementAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class BeginAST extends StatementAST {

  private final StatementAST statementAST;
  private SymbolTable scopeST;

  public BeginAST(ParserRuleContext ctx, StatementAST statementAST) {
    super(ctx);
    this.statementAST = statementAST;
  }

  @Override
  public void check() {

    // Create new symbol table(scope) for the statement.
    scopeST = ST = new SymbolTable(ST);

    // Check that the statement inside the begin block is valid.
    statementAST.check();

    // Reset the symbol table (i.e. return to the old scope).
    ST = ST.getEncSymTable();
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    List<Instruction> openScope = program.allocateStackSpace(scopeST);

    List<Instruction> instructions = statementAST.translate(registers);

    List<Instruction> closeScope = program.deallocateStackSpace(scopeST);

    openScope.addAll(instructions);
    openScope.addAll(closeScope);

    return openScope;

  }

}
