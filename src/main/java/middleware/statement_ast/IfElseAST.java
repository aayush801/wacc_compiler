package middleware.statement_ast;

import backend.instructions.Branch;
import backend.instructions.Compare;
import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.stack_instructions.LabelledInstruction;
import backend.labels.code.InstructionLabel;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.BOOL;
import java.util.List;
import middleware.ExpressionAST;
import middleware.StatementAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class IfElseAST extends StatementAST {

  private final ExpressionAST expressionAST;
  private final StatementAST firstStatAST;
  private final StatementAST secondStatAST;
  private SymbolTable ST1;
  private SymbolTable ST2;

  public IfElseAST(ParserRuleContext ctx, ExpressionAST expressionAST,
      StatementAST firstStatAST, StatementAST secondStatAST) {
    super(ctx);
    this.expressionAST = expressionAST;
    this.firstStatAST = firstStatAST;
    this.secondStatAST = secondStatAST;
  }

  @Override
  public void check() {

    // Verify that the condition expression is a valid expression.
    expressionAST.check();
    IDENTIFIER type = expressionAST.getType();

    if (type == null) {
      return;
    }

    // verify that the condition is a boolean.
    if (!(type instanceof BOOL)) {
      addError(new MismatchedTypes(
          expressionAST.ctx, expressionAST.getType(), new BOOL())
      );
      return;
    }

    // Create new symbol table(scope) for the 'then' statement.
    ST1 = ST = new SymbolTable(ST);

    // Verify the 'then' statement.
    firstStatAST.check();

    // Reset symbol table.
    ST = ST.getEncSymTable();

    // Create new symbol table(scope) for the 'else' statement.
    ST2 = ST = new SymbolTable(ST);

    // Verify the 'else' statement.
    secondStatAST.check();

    // Reset symbol table.
    ST = ST.getEncSymTable();

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    Register destination = registers.get(0);

    List<Instruction> instructions = expressionAST.translate(registers);

    instructions.add(new Compare(destination, new ImmediateNum(0)));

    LabelledInstruction body = new LabelledInstruction();
    LabelledInstruction rest = new LabelledInstruction();


    instructions.add(new Branch(ConditionCode.EQ, body.getLabel(), false));

    instructions.addAll(program.allocateStackSpace(ST1));
    instructions.addAll(firstStatAST.translate(registers));
    instructions.addAll(program.deallocateStackSpace(ST1));

    instructions.add(new Branch(rest.getLabel()));

    instructions.add(body);

    instructions.addAll(program.allocateStackSpace(ST2));
    instructions.addAll(secondStatAST.translate(registers));
    instructions.addAll(program.deallocateStackSpace(ST2));

    instructions.add(rest);

    return instructions;
  }

}
