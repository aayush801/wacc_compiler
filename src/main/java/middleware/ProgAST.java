package middleware;

import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.addr_modes.Address;
import backend.instructions.stack_instructions.Pop;
import backend.instructions.stack_instructions.Push;
import backend.labels.InstructionLabel;
import backend.registers.LinkRegister;
import backend.registers.ProgramCounter;
import backend.registers.Register;
import java.util.List;
import middleware.function_ast.FunctionDeclarationAST;
import middleware.statement_ast.StatementAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

// AST Node for the entire program.

public class ProgAST extends NodeAST {

  private final NodeASTList<FunctionDeclarationAST> functionDeclarationASTS;
  private final StatementAST statementAST;
  private SymbolTable scopeST;

  public ProgAST(ParserRuleContext ctx,
      NodeASTList<FunctionDeclarationAST> functionDeclarationASTS,
      StatementAST statementAST) {
    super(ctx);
    this.functionDeclarationASTS = functionDeclarationASTS;
    this.statementAST = statementAST;
  }

  @Override
  public void check() {
    scopeST = ST;
    // Go through any functions declared, and record them in the top symbol table.
    // This is done in a separate pass because a function body may call other functions
    // declared later on.
    for (FunctionDeclarationAST func : functionDeclarationASTS) {
      func.check();
    }

    // Now go through the actual function bodies.
    for (FunctionDeclarationAST func : functionDeclarationASTS) {
      func.checkStatement();
    }

    // Now check all the statements.
    statementAST.check();
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {

    // translate statement body
    List<Instruction> instructions = translateScope(scopeST, statementAST.translate(registers));

    // boiler plate front
    instructions.add(0, new Push(new LinkRegister()));

    // boiler plate back
    instructions.add(new Load(new Register(0), new Address("0")));
    instructions.add(new Pop(new ProgramCounter()));

    program.addMain(new InstructionLabel("main", instructions).setLastFunction());

    // need to add support for funcDeclarations

    // enclose statement body instructions in the main section
    return null;
  }
}
