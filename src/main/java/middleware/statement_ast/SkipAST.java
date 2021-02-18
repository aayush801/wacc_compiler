package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import java.util.List;
import org.antlr.v4.runtime.Token;

public class SkipAST extends StatementAST {

  // basic node for the skip statement.

  public SkipAST(Token token) {
    super(token);
  }

  @Override
  public void check() {
  }

}
