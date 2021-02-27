package middleware.expression_ast;

import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.Move;
import backend.instructions.addr_modes.Address;
import backend.instructions.addr_modes.ImmediateAddress;
import backend.labels.data.DataLabel;
import backend.operands.ImmediateChar;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import frontend.identifier_objects.basic_types.STR;
import java.util.ArrayList;
import java.util.List;
import middleware.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class LiteralsAST extends ExpressionAST {

  private final TYPE type;

  public LiteralsAST(ParserRuleContext ctx, TYPE type) {
    super(ctx);
    this.type = type;
  }

  @Override
  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    Register destination = registers.get(0);
    Instruction instruction = null;

    if (type instanceof INT) {
      int n = Integer.parseInt(ctx.getText());
      instruction = new Load(destination, new ImmediateAddress(n));

    } else if (type instanceof BOOL) {
      int n = 0;
      if (ctx.getText().equals("true")) {
        n = 1;
      }
      instruction = new Move(destination, new ImmediateNum(n));

    } else if (type instanceof CHAR) {
      // TODO: Reference compiler uses the 'B' flag for STR operations with characters. CHECK THIS!!
      // getting the final char of the text will implicitly solve escape char issues
      char c = ctx.getText().charAt(ctx.getText().length() - 2);
      instruction = new Move(destination, new ImmediateChar(c));

    } else if (type instanceof STR) {
      // add string to data section
      DataLabel dataLabel = new DataLabel(ctx.getText());
      program.addData(dataLabel);
      instruction = new Load(destination, new Address(dataLabel.getLabelName()));
    }

    //return new Collections.singleton(new Load(registers.get(0), new Address()));
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(instruction);
    return instructions;
  }
}
