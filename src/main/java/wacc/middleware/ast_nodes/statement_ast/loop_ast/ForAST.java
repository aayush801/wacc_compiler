package wacc.middleware.ast_nodes.statement_ast.loop_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.MismatchedTypes;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.basic_types.BOOL;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.middleware.ast_nodes.statement_ast.ChainedStatementAST;
import wacc.middleware.symbol_table.SymbolTable;

public class ForAST extends WhileAST {

  private final StatementAST initialisation;

  public ForAST(List<WaccError> errors, ParserRuleContext ctx,
      StatementAST initialisation, ExpressionAST condition,
      StatementAST afterthought, StatementAST body) {
    super(errors, ctx, condition,
        new ChainedStatementAST(errors, ctx, body, afterthought));
    this.initialisation = initialisation;
  }

  public ForAST(List<WaccError> errors, ParserRuleContext ctx,
      StatementAST initialisation, ExpressionAST condition,
      StatementAST body) {
    super(errors, ctx, condition, body);
    this.initialisation = initialisation;
  }

  @Override
  public void check() {
    insideLoops++;
    // check the expression
    // expression valid, now check the statement inside the body.
    // create a new scope(symbol table) for the statement.
    scopeST = ST = new SymbolTable(ST);
    initialisation.check();

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

    bodyAST.check();

    ST = ST.getEncSymTable();
    insideLoops--;
  }

  public StatementAST getInitialisation() {
    return initialisation;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
