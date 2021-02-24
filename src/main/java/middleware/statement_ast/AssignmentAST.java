package middleware.statement_ast;

import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.Store;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.instructions.addr_modes.ZeroOffset;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import java.util.ArrayList;
import java.util.List;
import middleware.StatementAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;


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

    List<Register> remainingRegs = new ArrayList<>(registers);
    remainingRegs.remove(0);

    // evaluate RHS first.
    List<Instruction> instructions = RHS.translate(registers);

    // TODO: DO THE CHAR CHECK FOR ALL 3 CASES!!!!!!!!!!!!!!!!!
    // Case when LHS is an identifier
    if (LHS.getIdentifier() != null) {
      VARIABLE varObj = (VARIABLE) scopeST.lookupAll(LHS.getIdentifier());
      int offset = program.SP.calculateOffset(varObj.getStackAddress());

      TYPE type = varObj.getType();
      if (type instanceof CHAR || type instanceof BOOL) {
        instructions.add(new Store(ConditionCode.NONE, target,
            new ImmediateOffset(program.SP, new ImmediateNum(offset)), true));
      } else {
        instructions.add(new Store(ConditionCode.NONE, target,
            new ImmediateOffset(program.SP, new ImmediateNum(offset)), false));
      }
    }

    if (LHS.getArrayElemAST() != null) {
      instructions.addAll(LHS.getArrayElemAST().translate(remainingRegs));
      instructions.add(new Store(target, new ZeroOffset(remainingRegs.get(0))));
    }

    return instructions;
  }
}
