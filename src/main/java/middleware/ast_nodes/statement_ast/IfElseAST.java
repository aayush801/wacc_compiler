package middleware.ast_nodes.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.BOOL;
import middleware.ExpressionAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.StatementAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class IfElseAST extends StatementAST {

  private final ExpressionAST expressionAST;
  private final StatementAST firstStatAST;
  private final StatementAST secondStatAST;
  private SymbolTable ST1;
  private SymbolTable ST2;

  public IfElseAST(ParserRuleContext ctx, ExpressionAST expressionAST,
      StatementAST firstStatAST, StatementAST secondStatAST) {
    super(ctx);
    this.expressionAST = expressionAST;
    this.firstStatAST = firstStatAST;
    this.secondStatAST = secondStatAST;
  }

  @Override
  public void check() {

    // Verify that the condition expression is a valid expression.
    expressionAST.check();
    IDENTIFIER type = expressionAST.getType();

    if (type == null) {
      return;
    }

    // verify that the condition is a boolean.
    if (!(type instanceof BOOL)) {
      addError(new MismatchedTypes(
          expressionAST.ctx, expressionAST.getType(), new BOOL())
      );
      return;
    }

    // Create new symbol table(scope) for the 'then' statement.
    ST1 = ST = new SymbolTable(ST);

    // Verify the 'then' statement.
    firstStatAST.check();

    // Reset symbol table.
    ST = ST.getEncSymTable();

    // Create new symbol table(scope) for the 'else' statement.
    ST2 = ST = new SymbolTable(ST);

    // Verify the 'else' statement.
    secondStatAST.check();

    // Reset symbol table.
    ST = ST.getEncSymTable();

  }


  public ExpressionAST getExpressionAST() {
    return expressionAST;
  }

  public SymbolTable getST1() {
    return ST1;
  }

  public SymbolTable getST2() {
    return ST2;
  }

  public StatementAST getFirstStatAST() {
    return firstStatAST;
  }

  public StatementAST getSecondStatAST() {
    return secondStatAST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
