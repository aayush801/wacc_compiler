package wacc.middleware.ast_nodes.statement_ast.loop_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.basic_types.BOOL;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class WhileAST extends StatementAST {

  protected final ExpressionAST conditionAST;
  protected final StatementAST bodyAST;
  protected SymbolTable scopeST;

  // Used to differentiate between while and dowhile.
  private final boolean isDoWhile;

  public WhileAST(List<WaccError> errors, ParserRuleContext ctx, ExpressionAST conditionAST,
      StatementAST body, boolean isDoWhile) {
    super(errors, ctx);
    this.conditionAST = conditionAST;
    this.bodyAST = body;
    this.isDoWhile = isDoWhile;
  }

  public WhileAST(List<WaccError> errors, ParserRuleContext ctx, ExpressionAST conditionAST,
      StatementAST bodyAST) {
    this(errors, ctx, conditionAST, bodyAST, false);
  }

  public boolean isDoWhile() {
    return isDoWhile;
  }

  @Override
  public void check() {
    insideLoops++;

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

    insideLoops--;
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
