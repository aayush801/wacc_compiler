package middleware.expression_ast;


import backend.instructions.Instruction;
import backend.registers.Register;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import java.util.List;
import middleware.NodeAST;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;


public class ExpressionAST extends NodeAST {

  protected TYPE type;

  public ExpressionAST(ParserRuleContext ctx) {
    super(ctx);
  }

  @Override
  public void check() {

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {

    return null;
  }

  public TYPE getType() {
    return type;
  }

  public boolean isIdentifier() {
    return false;
  }

}

