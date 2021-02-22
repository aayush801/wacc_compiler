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

  public String getIdentifier() {
    return identifier;
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

/*
* Where could this node be visited from?
*
* assign-rhs - Load the identifier into the first free register inside the assignRHSAST class.
*   e.g. int x = y. We know that there are type compatible.
*
* TODO Now:
* When evaluating an expression - i.e. in binOp or in UnOp, or in println, etc. this is a lot tbh :(
*   I would say this needs special handling i.e. when translating a binOp or UnOp, we need to evaluate the
*   subexpressions, so we should load it here instead by treating it specially
* when we declare a variable e.g. int x = 0 - do not need to change anything here
* assign-lhs (as an expression) - x = 2 needs a bit of thought tbh.
*
* TODO later:
* calling fst or snd on an ident that is a pair - to be done later
* array operations on an array name - to be done later
* in function stuff, i.e. parameters, arguments, etc. - to be done later
* */
