package middleware.statement_ast;

import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.Move;
import backend.instructions.addr_modes.Address;
import backend.instructions.addr_modes.ImmediateAddress;
import backend.labels.code.PrimitiveLabel;
import backend.operands.ImmediateNum;
import backend.primitive_functions.PrintFunctions;
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

    PrimitiveLabel primitiveLabel = null;
    if (type instanceof INT) {

      primitiveLabel = PrintFunctions.printInt(program);

    } else if (type instanceof CHAR) {

      instructions.add(new Branch("putchar", true));

    } else if (type instanceof BOOL) {

      primitiveLabel = PrintFunctions.printBool(program);

    } else if (type instanceof ARRAY || type instanceof PAIR) {

      if (type instanceof ARRAY) {

        TYPE arrayType = ((ARRAY) type).getType();

        if (arrayType instanceof CHAR) {

          // print array of chars as a string
          primitiveLabel = PrintFunctions.printString(program);

        }

      }

      // if NOT printing a char array, then print by reference
      if (primitiveLabel == null) {

        primitiveLabel = PrintFunctions.printReference(program);

      }

    } else if (type instanceof STR) {

      primitiveLabel = PrintFunctions.printString(program);

    }

    if (primitiveLabel != null) {
      instructions.add(new Branch(primitiveLabel.getLabelName(), true));
      program.addPrimitive(primitiveLabel);
    }

    if (newLine) {
      primitiveLabel = PrintFunctions.printLine(program);
      instructions.add(new Branch(primitiveLabel.getLabelName(), true));
      program.addPrimitive(primitiveLabel);
    }

    return instructions;
  }

}
