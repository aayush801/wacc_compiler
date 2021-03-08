package middleware.ast_nodes.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.BOOL;
import middleware.ExpressionAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.StatementAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class ForAST extends StatementAST {

  private SymbolTable scopeST;
  private final StatementAST initialisation, afterthought, body;
  private final ExpressionAST condition;
  private final boolean isDoWhile;

  public ForAST(ParserRuleContext ctx, StatementAST initialisation,
      ExpressionAST condition, StatementAST afterthought, StatementAST body,
      boolean isDoWhile) {
    super(ctx);
    this.condition = condition;
    this.initialisation =  initialisation;
    this.afterthought = afterthought;
    this.body = body;
    this.isDoWhile = isDoWhile;
  }

  public ForAST(ParserRuleContext ctx, StatementAST initialisation,
      ExpressionAST condition, StatementAST afterthought, StatementAST body) {
    this(ctx, initialisation, condition, afterthought, body, false);
  }

  public boolean isDoWhile() {
    return isDoWhile;
  }

  @Override
  public void check() {
    // check the expression
    condition.check();
    IDENTIFIER type = condition.getType();

    if (type == null) {
      // error has occurred elsewhere
      return;
    }

    // verify that the condition expression is a boolean.
    if (!(type instanceof BOOL)) {

      addError(new MismatchedTypes(condition.ctx, type, new BOOL()));

      return;

    }

    // expression valid, now check the statement inside the body.
    // create a new scope(symbol table) for the statement.
    scopeST = ST = new SymbolTable(ST);
    body.check();
    ST = ST.getEncSymTable();

  }

  public SymbolTable getScope() {
    return scopeST;
  }

  public ExpressionAST getCondition() {
    return condition;
  }

  public StatementAST getBody() {
    return body;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

  public StatementAST getInitialisation() {
    return initialisation;
  }

  public StatementAST getAfterthought() {
    return afterthought;
  }

}
