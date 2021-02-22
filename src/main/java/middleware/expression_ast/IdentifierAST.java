package middleware.expression_ast;

import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.operands.Immediate;
import backend.registers.Register;
import backend.registers.StackPointer;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;

import java.util.ArrayList;
import java.util.List;

import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class IdentifierAST extends ExpressionAST {

  private final String identifier;
  private TYPE type;
  private SymbolTable scopeST;

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
    scopeST = ST;

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
    Register target = registers.get(0);

    // lookup varObj in current and higher scopes to find the variable.
    VARIABLE varObj = (VARIABLE) scopeST.lookupAll(identifier);

    // calculate offset
    int offset = scopeST.getAllocatedStackMemory() - varObj.getOffset();

    // Simply load the identifier into the first register in the list.
    List<Instruction> ret = new ArrayList<>();
    ret.add(new Load(target, new ImmediateOffset(new StackPointer(), new Immediate(offset))));
    return ret;
  }
}

/*
* Where could this node be visited from?
*
* TODO Now:
*  I think the code should be in the translate thing tbh, and we juts restrict where we call it from no?
* assign-lhs (as an expression) - x = 2 needs a bit of thought tbh.
*
* TODO later:
* calling fst or snd on an ident that is a pair - to be done later
* array operations on an array name - to be done later
* in function stuff, i.e. parameters, arguments, etc. - to be done later
* */
