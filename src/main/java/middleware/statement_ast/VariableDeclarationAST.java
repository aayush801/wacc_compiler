package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.instructions.Store;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.operands.Immediate;
import backend.registers.Register;
import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.FUNCTION;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.VARIABLE;
import java.util.List;
import middleware.NodeAST;
import middleware.symbol_table.SymbolTable;
import middleware.types_ast.TypeAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class VariableDeclarationAST extends StatementAST {

  private final TypeAST typeAST;
  private final String varName;
  private final RHSAssignAST rhsAssignAST;
  public VARIABLE varObj;

  private SymbolTable scopeST;

  public VariableDeclarationAST(ParserRuleContext ctx, TypeAST typeAST, String varName,
      RHSAssignAST rhsAssignAST) {
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

      addError(new MismatchedTypes(ctx, rhsAssignAST.getType(), typeAST.getType()));
      return;

    }

    // If all checks pass, create new VARIABLE object with the type of the
    // typeAST node, and add this to the current symbol table.
    ST.index += typeAST.getType().getSize();
    scopeST = ST;
    varObj = new VARIABLE(typeAST.getType());
    varObj.setStackAddress(ST.index);

    NodeAST.ST.add(varName, varObj);
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    int sizeStack = scopeST.sizeOfVariablesDeclaredInScope();
    int offset = scopeST.index - varObj.getStackAddress();

    List<Instruction> instructions =  rhsAssignAST.translate(registers);
    Register source = registers.get(0);

    instructions.add(new Store(source, new ImmediateOffset(program.SP, new Immediate(offset))));

    return instructions;
  }

}
