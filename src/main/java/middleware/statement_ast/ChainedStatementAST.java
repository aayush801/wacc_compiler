package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import java.util.List;
import org.antlr.v4.runtime.Token;

public class ChainedStatementAST extends StatementAST {

  private final StatementAST statementAST1, statementAST2;

  public ChainedStatementAST(Token token, StatementAST statementAST1,
      StatementAST statementAST2) {
    super(token);
    this.statementAST1 = statementAST1;
    this.statementAST2 = statementAST2;
  }

  @Override
  public void check() {
    // Verify that both statements are valid.
    statementAST1.check();
    statementAST2.check();
  }

}
