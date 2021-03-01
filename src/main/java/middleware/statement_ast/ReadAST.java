package middleware.statement_ast;

import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Move;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.operands.ImmediateNum;
import backend.primitive_functions.ReadFunctions;
import backend.registers.Register;
import backend.registers.StackPointer;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import java.util.ArrayList;
import java.util.List;
import middleware.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ReadAST extends StatementAST {

  private final LHSAssignAST LHS;

  public ReadAST(ParserRuleContext ctx, LHSAssignAST LHS) {
    super(ctx);
    this.LHS = LHS;
  }

  @Override
  public void check() {

    // Verify that the LHS is a valid assignLHS.
    LHS.check();

    // Verify that type is an INT or CHAR.
    IDENTIFIER type = LHS.getType();

    if (type != null) {

      if (!(type instanceof INT || type instanceof CHAR)) {

        addError(new MismatchedTypes(ctx, type, new INT(), new CHAR()));

      }
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    // read x is probably the only case right. Well, it could also be x[1], or fst(x).
    // So, let's say evaluate x (via the LHS.translate right), and this could be arbitrarily complex.
    // Then, we use getChar or something cuz it can only be int or char, which we have verified.

    Register target = registers.get(0);

    List<Instruction> ret = new ArrayList<>();

    List<Instruction> temp = LHS.translate(registers);

    // Translate the LHS we want to read into.
    if (LHS.getIdentifier() != null) {
      int offset = LHS.getOffset();
      ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, new StackPointer(), new ImmediateNum(offset), false));
    } else {
      ret.addAll(temp);
    }

    ret.add(new Move(new Register(0), target));

    if (LHS.getIsChar()) {
      ret.add(new Branch("p_read_char", true));
      program.addPrimitive(ReadFunctions.readCharFunction(program));
    } else {
      ret.add(new Branch("p_read_int", true));
      program.addPrimitive(ReadFunctions.readIntFunction(program));
    }

    return ret;
  }
}
