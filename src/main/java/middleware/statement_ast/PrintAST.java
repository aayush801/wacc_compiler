package middleware.statement_ast;

import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.labels.DataLabel;
import backend.registers.Register;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.ARRAY;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import frontend.identifier_objects.basic_types.PAIR;
import frontend.identifier_objects.basic_types.STR;
import java.util.ArrayList;
import java.util.List;
import middleware.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class PrintAST extends StatementAST {

  private final ExpressionAST expr;
  private TYPE type;

  // Used to differentiate between print and println.
  private final boolean newLine;

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
    List<Instruction> instructions = expr.translate(registers);
    Register destination = registers.get(0);

    if (type instanceof INT) {

      instructions.add(new Branch("p_print_int", true));

    } else if (type instanceof CHAR) {

      instructions.add(new Branch("putchar", true));

    } else if (type instanceof BOOL) {

      instructions.add(new Branch("p_print_bool", true));

    } else if (type instanceof ARRAY || type instanceof PAIR) {

      instructions.add(new Branch("p_print_reference", true));

    } else if (type instanceof STR) {

      instructions.add(new Branch("p_print_string", true));

    }

    if (newLine) {

      instructions.add(new Branch("p_print_ln", true));

    }

    return instructions;
  }

}
