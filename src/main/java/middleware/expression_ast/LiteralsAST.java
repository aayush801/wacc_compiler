package middleware.expression_ast;

import backend.instructions.Instruction;
import backend.instructions.Label;
import backend.instructions.Load;
import backend.instructions.amodes.Address;
import backend.registers.Register;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.STR;
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
    if(type instanceof STR){
      // DataSection.add(new Label("msg_"))
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {

  //  return new Collections.singleton(new Load(registers.get(0), new Address()));
    return null;
  }
}
