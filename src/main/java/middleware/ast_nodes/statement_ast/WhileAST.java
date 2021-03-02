package middleware.ast_nodes.statement_ast;

import middleware.NodeASTVisitor;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.BOOL;
import middleware.ExpressionAST;
import middleware.ast_nodes.StatementAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class WhileAST extends StatementAST {

  private final ExpressionAST expressionAST;
  private final StatementAST statementAST;
  private SymbolTable scopeST;

  public WhileAST(ParserRuleContext ctx, ExpressionAST expressionAST,
      StatementAST statementAST) {
    super(ctx);
    this.expressionAST = expressionAST;
    this.statementAST = statementAST;
  }

  @Override
  public void check() {
    // check the expression
    expressionAST.check();
    IDENTIFIER type = expressionAST.getType();

    if (type == null) {
      // error has occurred elsewhere
      return;
    }

    // verify that the condition expression is a boolean.
    if (!(type instanceof BOOL)) {

      addError(new MismatchedTypes(
          expressionAST.ctx, type, new BOOL())
      );

      return;

    }

    // expression valid, now check the statement inside the body.
    // create a new scope(symbol table) for the statement.
    scopeST = ST = new SymbolTable(ST);
    statementAST.check();
    ST = ST.getEncSymTable();

  }

  public SymbolTable getScope() {
    return scopeST;
  }

  public ExpressionAST getExpressionAST() {
    return expressionAST;
  }

  public StatementAST getStatementAST() {
    return statementAST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
