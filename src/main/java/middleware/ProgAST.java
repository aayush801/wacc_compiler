package middleware;

import backend.ProgramGenerator;
import backend.instructions.EOC;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.addr_modes.Address;
import backend.labels.code.CodeLabel;
import backend.primitive_functions.RuntimeError;
import backend.registers.Register;
import java.util.ArrayList;
import java.util.List;
import middleware.function_ast.FunctionDeclarationAST;
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
    scopeST = ST = SymbolTable.TopSymbolTable();
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
    program = new ProgramGenerator();

    // translate function declarations
    for (FunctionDeclarationAST func : functionDeclarationASTS) {
      func.translate(registers);
    }
    List<Instruction> instructions = new ArrayList<>();
    program.pushLR(instructions);

    // translate statement body (encapsulates the statement in a new scope)
    instructions.addAll(program.allocateStackSpace(scopeST));
    instructions.addAll(statementAST.translate(registers));
    instructions.addAll(program.deallocateStackSpace(scopeST));

    // add exit code 0 on successful exit
    instructions.add(new Load(new Register(0), new Address("0")));
    program.popPC(instructions);
    instructions.add(new EOC());

    program.addCode(new CodeLabel("main", instructions));

    return null;
  }

}
