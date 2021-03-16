package wacc.middleware.ast_nodes.expression_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.STACK_OBJECT;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class IdentifierAST extends ExpressionAST {

  private final String identifier;
  private TYPE type;
  private SymbolTable scopeST;

  public IdentifierAST(List<WaccError> errors, ParserRuleContext ctx,
      String identifier) {
    super(errors, ctx);
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

    if (obj instanceof STACK_OBJECT) {
      type = ((STACK_OBJECT) obj).getType();
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
