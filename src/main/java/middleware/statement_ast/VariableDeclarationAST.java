package middleware.statement_ast;

import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.Store;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.*;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import java.util.List;
import middleware.NodeAST;
import middleware.StatementAST;
import middleware.TypeAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class VariableDeclarationAST extends StatementAST {

  private final TypeAST typeAST;
  private final String varName;
  private final RHSAssignAST rhsAssignAST;
  public STACK_OBJECT varObj;

  private SymbolTable scopeST;

  public VariableDeclarationAST(ParserRuleContext ctx, TypeAST typeAST,
      String varName, RHSAssignAST rhsAssignAST) {
    super(ctx);
    this.typeAST = typeAST;
    this.varName = varName;
    this.rhsAssignAST = rhsAssignAST;
  }

  @Override
  public void check() {
    // check the type of the declaration.
    typeAST.check();

    // verify that the type is valid.
    if (typeAST.getType() == null) {
      return;
    }

    // lookup name in current symbol table.
    IDENTIFIER variable = NodeAST.ST.lookup(varName);

    // if variable already present in current symbol table and is not a
    // function, this is not valid as it is a duplicate identifier.
    if (variable != null && !(variable instanceof FUNCTION)) {
      addError(new DuplicateIdentifier(ctx));
      return;
    }

    // check the RHS now.
    rhsAssignAST.check();

    // verify that the RHS is not null.
    if (rhsAssignAST.getType() == null) {
      return;
    }

    // Verify that LHS and RHS are type compatible.
    if (!isCompatible(typeAST.getType(), rhsAssignAST.getType())) {

      addError(new MismatchedTypes(ctx, rhsAssignAST.getType(),
          typeAST.getType()));
      return;

    }

    // If all checks pass, create new VARIABLE object with the type of the
    // typeAST node, and add this to the current symbol table.

    scopeST = ST;
    varObj = new VARIABLE(typeAST.getType());
   // System.out.println(varObj);
    NodeAST.ST.add(varName, varObj);
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    Register destination = registers.get(0);
   // System.out.println(program.SP.getStackPtr());

    List<Instruction> instructions = rhsAssignAST.translate(registers);

   // System.out.println(program.SP.getStackPtr());

    // Amount of bytes to add to the stack pointer to get address of variable
    int stackAddress = program.SP.push(varObj); //pushes varObj onto stack
    int offset = program.SP.calculateOffset(
        stackAddress); // gets address of var in respect to the current stack pointer
    TYPE type = typeAST.getType();
    instructions.add(new Store(ConditionCode.NONE, destination,
        new ImmediateOffset(program.SP, new ImmediateNum(offset)), type.getSize()));

    return instructions;
  }

}
