package middleware.statement_ast;

import backend.NodeASTVisitor;
import backend.instructions.Instruction;
import backend.registers.Register;
import java.util.List;
import middleware.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ChainedStatementAST extends StatementAST {

  private final StatementAST statementAST1, statementAST2;

  public ChainedStatementAST(ParserRuleContext ctx, StatementAST statementAST1,
      StatementAST statementAST2) {
    super(ctx);
    this.statementAST1 = statementAST1;
    this.statementAST2 = statementAST2;
  }

  @Override
  public void check() {
    // Verify that both statements are valid.
    statementAST1.check();
    statementAST2.check();
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {

    List<Instruction> instructionsList1 = statementAST1.translate(registers);
    List<Instruction> instructionsList2 = statementAST2.translate(registers);

    instructionsList1.addAll(instructionsList2);

    return instructionsList1;
  }

  @Override
  public List<Instruction> accept(NodeASTVisitor visitor) {
    return (List<Instruction>) visitor.visit(this);
  }

}
