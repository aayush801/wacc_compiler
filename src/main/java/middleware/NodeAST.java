package middleware;

import backend.ProgramGenerator;
import backend.instructions.Instruction;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import errors.WaccError;
import errors.semantic_errors.WaccSemanticError;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import java.util.ArrayList;
import java.util.List;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class NodeAST implements NodeASTInterface {

  // A general Node of the AST.

  // One symbol table reference, updated throughout when required.
  // Initially this is set to the top level symbol table.
  protected static SymbolTable ST = SymbolTable.TopSymbolTable();
  protected static ProgramGenerator program = new ProgramGenerator();
  private static List<WaccError> semanticErrors;

  public static void setSemanticErrors(List<WaccError> semanticErrors) {
    NodeAST.semanticErrors = semanticErrors;
  }

  public static void reset() {
    ST = SymbolTable.TopSymbolTable();
    program = new ProgramGenerator();
  }

  public String translate() {
    translate(program.registers);
    return program.toString();
  }


  public ParserRuleContext ctx;

  public NodeAST(ParserRuleContext ctx) {
    this.ctx = ctx;
  }

  protected void addError(WaccSemanticError error) {
    semanticErrors.add(error);
  }

  @Override
  public abstract void check();

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return new ArrayList<>();
  }

  protected boolean isCompatible(IDENTIFIER t1, IDENTIFIER t2) {
    return t1 instanceof TYPE && t2 instanceof TYPE && t2.equals(t1);
  }

  public List<Instruction> translateScope(SymbolTable scopeST,
      List<Instruction> instructions) {
    int sizeOfVariablesDeclaredInScope = scopeST.getAllocatedInThisScope();

    // no variables declared in this scope, so just return.
    if (sizeOfVariablesDeclaredInScope == 0) {
      return instructions;
    }
    Instruction decrementStack =
        new Arithmetic(ArithmeticOpcode.SUB, program.SP, program.SP,
            new ImmediateNum(sizeOfVariablesDeclaredInScope), false);

    instructions.add(0, decrementStack);

    Instruction incrementStack =
        new Arithmetic(ArithmeticOpcode.ADD, program.SP, program.SP,
            new ImmediateNum(sizeOfVariablesDeclaredInScope), false);

    instructions.add(incrementStack);
    return instructions;
  }
}
