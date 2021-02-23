package middleware.statement_ast;

import backend.instructions.Branch;
import backend.instructions.Compare;
import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.stack_instructions.LabelledInstruction;
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

public class WhileAST extends StatementAST {

  private final ExpressionAST expressionAST;
  private final StatementAST statementAST;

  public WhileAST(ParserRuleContext ctx, ExpressionAST expressionAST,
      StatementAST statementAST) {
    super(ctx);
    this.expressionAST = expressionAST;
    this.statementAST = statementAST;
  }

  @Override
  public void check() {
    // check the expression
    expressionAST.check();
    IDENTIFIER type = expressionAST.getType();

    if (type == null) {
      // error has occurred elsewhere
      return;
    }

    // verify that the condition expression is a boolean.
    if (!(expressionAST.getType() instanceof BOOL)) {

      addError(new MismatchedTypes(
          expressionAST.ctx, expressionAST.getType(), new BOOL())
      );

      return;

    }

    // expression valid, now check the statement inside the body.
    // create a new scope(symbol table) for the statement.
    ST = new SymbolTable(ST);
    statementAST.check();
    ST = ST.getEncSymTable();

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    Register destination = registers.get(0);
    List<Instruction> instructions = new ArrayList<>();

    int labelNumber = program.nextLabelNumber();
    program.nextLabelNumber();

    instructions.add(new Branch("L" + labelNumber));

    List<Instruction> statement = statementAST.translate(registers);
    Instruction start = null;
    if (statement.size() > 0) {
      start = statement.get(0);
      statement.remove(0);
    }
    instructions.add(new LabelledInstruction("L" + (labelNumber + 1), start));
    instructions.addAll(statement);

    List<Instruction> condition = expressionAST.translate(registers);
    if (condition.size() > 0) {
      start = condition.get(0);
      condition.remove(0);
    }
    instructions.add(new LabelledInstruction("L" + labelNumber, start));
    instructions.addAll(condition);
    instructions.add(new Compare(destination, ImmediateNum.ONE));
    instructions.add(new Branch(ConditionCode.EQ, "L" + (labelNumber + 1),
        false));

    return instructions;
  }

}
