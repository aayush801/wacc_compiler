package wacc.middleware;

import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.TYPE;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.middleware.symbol_table.SymbolTable;

public abstract class NodeAST implements NodeASTInterface {

  // A general Node of the AST.
  // One symbol table reference, updated throughout when required.
  // Initially this is set to the top level symbol table.
  protected static SymbolTable ST;
  protected List<WaccError> errors;
  public ParserRuleContext ctx;
  protected int insideLoops = 0;

  public NodeAST(List<WaccError> errors, ParserRuleContext ctx) {
    this.ctx = ctx;
    this.errors = errors;
  }

  public void addError(WaccError error) { errors.add(error); }

  @Override
  public abstract void check();

  protected boolean isCompatible(IDENTIFIER t1, IDENTIFIER t2) {

    return t1 instanceof TYPE && t2 instanceof TYPE && t2.equals(t1);

  }

  public ParserRuleContext getCtx() {
    return ctx;
  }


  public List<WaccError> getErrors() {
    return errors;
  }

}
