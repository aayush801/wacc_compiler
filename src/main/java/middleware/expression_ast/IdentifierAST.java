package middleware.expression_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class IdentifierAST extends ExpressionAST {

  private final String identifier;
  private TYPE type;

  public IdentifierAST(ParserRuleContext ctx, String identifier) {
    super(ctx);
    this.identifier = identifier;
  }

  @Override
  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    // find the object corresponding to the identifier in the lookup table
    IDENTIFIER obj = ST.lookupAll(identifier);

    if (obj == null) {
      addError(new Undefined(ctx));
      return;
    }

    if (obj instanceof VARIABLE) {
      type = ((VARIABLE) obj).getType();
      return;
    }

    if (obj instanceof PARAM) {
      type = ((PARAM) obj).getType();
      return;
    }

    type = (TYPE) obj;
  }

  @Override
  public boolean isIdentifier() {
    return true;
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return super.translate(registers);
  }
}
