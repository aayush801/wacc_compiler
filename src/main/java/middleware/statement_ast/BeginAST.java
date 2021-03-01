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
    // save the stack state in the symbol table
    scopeST.saveStackState(program.SP);

    List<Instruction> instructions = program.allocateStackSpace(scopeST);
    instructions.addAll(statementAST.translate(registers));
    instructions.addAll(program.deallocateStackSpace(scopeST));

    // save the stack state in the symbol table
    scopeST.restoreStackState(program.SP);

    return instructions;

  }

}
