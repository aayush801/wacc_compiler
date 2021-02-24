package middleware.statement_ast;

import backend.instructions.Branch;
import backend.instructions.Compare;
import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.stack_instructions.LabelledInstruction;
import backend.labels.Label;
import backend.labels.code.InstructionLabel;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.BOOL;
import java.util.ArrayList;
import java.util.List;
import middleware.expression_ast.ExpressionAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class IfElseAST extends StatementAST {

  private final ExpressionAST expressionAST;
  private final StatementAST firstStatAST;
  private final StatementAST secondStatAST;

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
    ST = new SymbolTable(ST);

    // Verify the 'then' statement.
    firstStatAST.check();

    // Reset symbol table.
    ST = ST.getEncSymTable();

    // Create new symbol table(scope) for the 'else' statement.
    ST = new SymbolTable(ST);

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

    int labelNumber = program.nextLabelNumber();
    program.nextLabelNumber();
    instructions.add(new Branch(ConditionCode.EQ,
        "L" + labelNumber, false));

    instructions.addAll(firstStatAST.translate(registers));
    instructions.add(new Branch("L" + (labelNumber + 1)));

    List<Instruction> second = secondStatAST.translate(registers);
    Instruction start = null;
    if (second.size() > 0) {
      start = second.get(0);
      second.remove(0);
    }
    instructions.add(new LabelledInstruction("L" + labelNumber, start));
    instructions.addAll(second);
    instructions.add(new LabelledInstruction("L" + (labelNumber + 1), null));

    return instructions;
  }

}
