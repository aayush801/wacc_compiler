package middleware;

import backend.instructions.Instruction;
import backend.instructions.Label;
import backend.registers.Register;
import errors.WaccError;
import errors.semantic_errors.WaccSemanticError;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import java.util.ArrayList;
import java.util.List;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.Token;

public abstract class NodeAST implements NodeASTInterface {

  // A general Node of the AST.

  // One symbol table reference, updated throughout when required.
  // Initially this is set to the top level symbol table.
  protected static SymbolTable ST = SymbolTable.TopSymbolTable();

  protected static List<Label> DataSection = new ArrayList<>();
  protected static List<Label> TextSection = new ArrayList<>();
  protected static List<Label> MainSection = new ArrayList<>();

  private static List<WaccError> semanticErrors;
  public Token token;

  public NodeAST(Token token) {
    this.token = token;
  }

  public static void setSemanticErrors(List<WaccError> semanticErrors) {
    NodeAST.semanticErrors = semanticErrors;
  }

  protected void addError(WaccSemanticError error) {
    semanticErrors.add(error);
  }

  public static void reset() {
    ST = SymbolTable.TopSymbolTable();
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
}
