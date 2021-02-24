package middleware.statement_ast;

import backend.functions.PrintFunctions;
import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Move;
import backend.labels.code.PrimitiveLabel;
import backend.registers.Register;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.ARRAY;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import frontend.identifier_objects.basic_types.PAIR;
import frontend.identifier_objects.basic_types.STR;
import java.util.List;
import middleware.ExpressionAST;
import middleware.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class PrintAST extends StatementAST {

  private final ExpressionAST expr;
  // Used to differentiate between print and println.
  private final boolean newLine;
  private TYPE type;

  public PrintAST(ParserRuleContext ctx, ExpressionAST expr, boolean newLine) {
    super(ctx);
    this.expr = expr;
    this.newLine = newLine;
  }

  @Override
  public void check() {
    expr.check();
    type = expr.getType();
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    Register dest = registers.get(0);
    // translate expression
    List<Instruction> instructions = expr.translate(registers);

    // move result of expression to register 0
    instructions.add(new Move(new Register(0), dest));

    PrimitiveLabel label = null;

    if (type instanceof INT) {
      label = PrintFunctions.printInt(program);

    } else if (type instanceof CHAR) {

      instructions.add(new Branch("putchar", true));

    } else if (type instanceof BOOL) {

      label = PrintFunctions.printBool(program);

    } else if (type instanceof ARRAY || type instanceof PAIR) {

      instructions.add(new Branch("p_print_reference", true));

    } else if (type instanceof STR) {

      label = PrintFunctions.printString(program);

    }

    if (label != null) {
      instructions.add(new Branch(label.getLabelName(), true));
      program.addCode(label);
    }

    if (newLine) {
      label = PrintFunctions.printLine(program);
      instructions.add(new Branch(label.getLabelName(), true));
      program.addCode(label);
    }

    return instructions;
  }

}
