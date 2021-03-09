package middleware.ast_nodes.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.BOOL;
import middleware.ExpressionAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.StatementAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class WhileAST extends StatementAST {

  private final ExpressionAST conditionAST;
  private final StatementAST bodyAST;
  private SymbolTable scopeST;
  private final boolean isDoWhile;

  public WhileAST(ParserRuleContext ctx, ExpressionAST conditionAST,
      StatementAST body, boolean isDoWhile) {
    super(ctx);
    this.conditionAST = conditionAST;
    this.bodyAST = body;
    this.isDoWhile = isDoWhile;
  }

  public WhileAST(ParserRuleContext ctx, ExpressionAST conditionAST,
      StatementAST bodyAST) {
    this(ctx, conditionAST, bodyAST, false);
  }

  public boolean isDoWhile() {
    return isDoWhile;
  }

  @Override
  public void check() {
    // check the expression
    conditionAST.check();
    IDENTIFIER type = conditionAST.getType();

    if (type == null) {
      // error has occurred elsewhere
      return;
    }

    // verify that the condition expression is a boolean.
    if (!(type instanceof BOOL)) {

      addError(new MismatchedTypes(
          conditionAST.ctx, type, new BOOL())
      );

      return;

    }

    // expression valid, now check the statement inside the body.
    // create a new scope(symbol table) for the statement.
    scopeST = ST = new SymbolTable(ST);
    bodyAST.check();
    ST = ST.getEncSymTable();

  }

  public SymbolTable getScope() {
    return scopeST;
  }

  public ExpressionAST getConditionAST() {
    return conditionAST;
  }

  public StatementAST getStatementAST() {
    return bodyAST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
