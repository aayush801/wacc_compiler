package middleware.expression_ast;

import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.Move;
import backend.instructions.addr_modes.ImmediateAddress;
import backend.operands.Immediate;
import backend.registers.Register;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.antlr.v4.runtime.Token;

public class LiteralsAST extends ExpressionAST {

  private final TYPE type;

  public LiteralsAST(Token token, TYPE type) {
    super(token);
    this.type = type;
  }

  @Override
  public IDENTIFIER getType() {
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
      int n = Integer.parseInt(token.getText());
      instruction = new Load(destination, new ImmediateAddress(n));

    } else if (type instanceof BOOL) {
      int n = 0;
      if (token.getText().equals("true")) {
        n = 1;
      }
      instruction = new Move(destination, new Immediate(n));

    } else if (type instanceof CHAR) {
      char c = token.getText().charAt(0);
      int n = (int) c;
      instruction = new Move(destination, new Immediate(n));
    }

    //return new Collections.singleton(new Load(registers.get(0), new Address()));
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(instruction);
    return instructions;
  }
}
