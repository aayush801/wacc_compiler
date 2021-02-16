package middleware;

import backend.instructions.Instruction;
import backend.instructions.Label;
import backend.instructions.Load;
import backend.instructions.stack_instructions.Pop;
import backend.instructions.stack_instructions.Push;
import backend.instructions.amodes.Address;
import backend.registers.LinkRegister;
import backend.registers.ProgramCounter;
import backend.registers.Register;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import middleware.function_ast.FunctionDeclarationAST;
import middleware.statement_ast.StatementAST;
import org.antlr.v4.runtime.Token;

// AST Node for the entire program.

public class ProgAST extends NodeAST {

  private final NodeASTList<FunctionDeclarationAST> functionDeclarationASTS;
  private final StatementAST statementAST;

  public ProgAST(Token token,
      NodeASTList<FunctionDeclarationAST> functionDeclarationASTS,
      StatementAST statementAST) {
    super(token);
    this.functionDeclarationASTS = functionDeclarationASTS;
    this.statementAST = statementAST;
  }

  @Override
  public void check() {

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
    List<Instruction> instructions = statementAST.translate(registers);

    // boiler plate front
    instructions.add(0, new Push(new LinkRegister()));


    // boiler plate back
    instructions.add(new Load(new Register(0), new Address("0")));
    instructions.add(new Pop(new ProgramCounter()));

    //instructions.add(new Label(".ltorg", new ArrayList<>()));

    // need to add support for funcDeclarations

    // enclose statement body instructions in the main section
    return Collections.singletonList(new Label("main", instructions));
  }
}