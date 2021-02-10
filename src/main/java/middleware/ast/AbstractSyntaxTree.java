package middleware.ast;

import errors.WaccError;
import java.util.List;
import semantic_parser.SemanticErrorListener;
import symbol_table.SymbolTable;

public class AbstractSyntaxTree {

  protected SemanticErrorListener semanticErrors;
  protected SymbolTable symbolTable;

  public void setValues(SemanticErrorListener semanticErrors, SymbolTable symbolTable) {
    this.semanticErrors = semanticErrors;
    this.symbolTable = symbolTable;
  }

  private void error(WaccError e) {
    semanticErrors.add(e);
  }
}
