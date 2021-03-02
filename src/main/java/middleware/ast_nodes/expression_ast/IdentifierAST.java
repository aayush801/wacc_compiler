package middleware.ast_nodes.expression_ast;

import middleware.NodeASTVisitor;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import middleware.ExpressionAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class IdentifierAST extends ExpressionAST {

  private final String identifier;
  private TYPE type;
  private SymbolTable scopeST;

  public IdentifierAST(ParserRuleContext ctx, String identifier) {
    super(ctx);
    this.identifier = identifier;
  }

  @Override
  public TYPE getType() {
    return type;
  }

  public String getIdentifier() {
    return identifier;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }

  @Override
  public void check() {
    // find the object corresponding to the identifier in the lookup table
    IDENTIFIER obj = ST.lookupAll(identifier);
    scopeST = ST;

    if (obj == null) {
      addError(new Undefined(ctx));
      return;
    }

    if (obj instanceof VARIABLE) {
      type = ((VARIABLE) obj).getType();
      return;
    }

    if (obj instanceof PARAM) {
      type = ((PARAM) obj).getType();
      return;
    }

    type = (TYPE) obj;
  }

  @Override
  public boolean isIdentifier() {
    return true;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
