package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.instructions.Move;
import backend.instructions.stack_instructions.Pop;
import backend.registers.Register;
import errors.semantic_errors.GlobalScope;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.TYPE;
import java.util.List;
import middleware.ExpressionAST;
import middleware.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ReturnAST extends StatementAST {

  private final ExpressionAST expressionAST;
  private TYPE type;

  public ReturnAST(ParserRuleContext ctx, ExpressionAST expressionAST) {
    super(ctx);
    this.expressionAST = expressionAST;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {

    // Verify that the provided expression is a valid expression
    expressionAST.check();

    TYPE type = expressionAST.getType();

    if (ST.getEncSymTable() == null) {

      // Trying to return from the main/global scope.
      addError(new GlobalScope(ctx));
      return;

    }

    if (type == null) {
      // error has occurred elsewhere
      return;
    }

    if (!(isCompatible(type, ST.getScopeReturnType()))) {

      // Provided return type and
      // expected return type(of the function that return is in) do not match.
      addError(
          new MismatchedTypes(
              expressionAST.ctx, type, ST.getScopeReturnType())
      );

      return;

    }

    // Valid type, so set the type of this AST node.
    this.type = type;

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    Register dest = registers.get(0);
    List<Instruction> instructions = expressionAST.translate(registers);
    instructions.add(new Move(new Register(0), dest));
    instructions.add(new Pop(program.PC));

    return instructions;
  }

}
