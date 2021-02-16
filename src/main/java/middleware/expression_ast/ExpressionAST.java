package middleware.expression_ast;


import backend.instructions.Instruction;
import backend.registers.Register;
import frontend.identifier_objects.IDENTIFIER;
import java.util.List;
import middleware.NodeAST;
import org.antlr.v4.runtime.Token;


public class ExpressionAST extends NodeAST {

  protected IDENTIFIER type;

  public ExpressionAST(Token token) {
    super(token);
  }

  @Override
  public void check() {

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return null;
  }

  public IDENTIFIER getType() {
    return type;
  }

  public boolean isIdentifier() {
    return false;
  }

}

