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
import middleware.ExpressionAST;
import middleware.StatementAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class WhileAST extends StatementAST {

  private final ExpressionAST expressionAST;
  private final StatementAST statementAST;
  private SymbolTable scopeST;

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
    scopeST = ST = new SymbolTable(ST);
    statementAST.check();
    ST = ST.getEncSymTable();

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    Register destination = registers.get(0);
    List<Instruction> instructions = new ArrayList<>();

    LabelledInstruction rest = new LabelledInstruction();
    LabelledInstruction body = new LabelledInstruction();

    instructions.add(new Branch(body.getLabel()));

    // translate statement in new scope
    instructions.add(body);

    instructions.addAll(program.allocateStackSpace(scopeST));
    instructions.addAll(statementAST.translate(registers));
    instructions.addAll(program.deallocateStackSpace(scopeST));

    // translate instructions after loop body
    instructions.add(rest);
    instructions.addAll(expressionAST.translate(registers));

    instructions.add(new Compare(destination, new ImmediateNum(1)));
    instructions.add(new Branch(ConditionCode.EQ, rest.getLabel(), false));

    return instructions;
  }

}
