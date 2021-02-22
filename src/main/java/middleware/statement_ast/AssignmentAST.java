package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.instructions.Store;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.operands.Immediate;
import backend.registers.Register;
import backend.registers.StackPointer;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import java.util.List;

import frontend.identifier_objects.VARIABLE;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;


public class AssignmentAST extends StatementAST {

  private final LHSAssignAST LHS;
  private final RHSAssignAST RHS;
  private SymbolTable scopeST;

  public AssignmentAST(ParserRuleContext ctx, LHSAssignAST LHS,
      RHSAssignAST RHS) {
    super(ctx);
    this.LHS = LHS;
    this.RHS = RHS;
  }

  @Override
  public void check() {
    LHS.check();
    RHS.check();

    scopeST = ST;

    // LHS is an IDENTIFIER because it could be an Identifier(Ident).
    IDENTIFIER leftType = LHS.getType();

    // RHS must be a TYPE from the parser rules.
    TYPE rightType = RHS.getType();

    if (leftType != null && rightType != null) {

      if (!isCompatible(leftType, rightType)) {
        // LHS and RHS not type compatible.
        addError(new MismatchedTypes(ctx, rightType, leftType));
      }

    }

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {

    // Result of evaluating RHS will be stored here.
    Register target = registers.get(0);

    // evaluate RHS first.
    List<Instruction> instructions = RHS.translate(registers);

    // Case when LHS is an identifier
    if (LHS.getIdentifier() != null) {
      VARIABLE varObj = (VARIABLE) scopeST.lookupAll(LHS.getIdentifier());
      int offset = scopeST.getAllocatedStackMemory() - varObj.getOffset();
      instructions.add(new Store(target,
          new ImmediateOffset(new StackPointer(), new Immediate(offset))));
    }

    return instructions;
  }
}
