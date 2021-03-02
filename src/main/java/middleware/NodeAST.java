package middleware;

import backend.NodeASTVisitor;
import backend.ProgramGenerator;
import errors.WaccError;
import errors.semantic_errors.WaccSemanticError;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import java.util.List;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class NodeAST implements NodeASTInterface {

  // A general Node of the AST.
  // One symbol table reference, updated throughout when required.
  // Initially this is set to the top level symbol table.
  protected static SymbolTable ST;
  protected static ProgramGenerator program = new ProgramGenerator();

  private static List<WaccError> semanticErrors;

  public ParserRuleContext ctx;

  public NodeAST(ParserRuleContext ctx) {
    this.ctx = ctx;
  }

  public static void setSemanticErrors(List<WaccError> semanticErrors) {
    NodeAST.semanticErrors = semanticErrors;
  }

  public void addError(WaccSemanticError error) {
    semanticErrors.add(error);
  }

  @Override
  public abstract void check();

  protected boolean isCompatible(IDENTIFIER t1, IDENTIFIER t2) {
    return t1 instanceof TYPE && t2 instanceof TYPE && t2.equals(t1);
  }

  public ParserRuleContext getCtx() {
    return ctx;
  }

  public SymbolTable getST() {
    return ST;
  }

  public void setST(SymbolTable ST) {
    NodeAST.ST = ST;
  }

  public ProgramGenerator getProgram() {
    return program;
  }

  public void setProgram(ProgramGenerator program) {
    NodeAST.program = program;
  }

}
