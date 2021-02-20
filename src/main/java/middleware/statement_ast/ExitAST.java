package middleware.statement_ast;

import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Move;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.INT;
import java.util.List;
import middleware.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class ExitAST extends StatementAST {

  private final ExpressionAST expressionAST;

  public ExitAST(ParserRuleContext ctx, ExpressionAST expressionAST) {
    super(ctx);
    this.expressionAST = expressionAST;
  }

  @Override
  public void check() {

    // Verify that the expr passed to exit is a valid expression.
    expressionAST.check();
    IDENTIFIER type = expressionAST.getType();
    if(type == null){
      return;
    }

    // Verify that the expression is an INT.
    if (!(type instanceof INT)) {
      addError(new MismatchedTypes(
          expressionAST.ctx, expressionAST.getType(), new INT())
      );
    }

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    List<Instruction> instructions = expressionAST.translate(registers);
    Register intReg = registers.get(0);

    if(intReg.getNumber() != 0) instructions.add(new Move(new Register(0), intReg));

    instructions.add(new Branch("exit", true));

    return instructions;
  }
}
