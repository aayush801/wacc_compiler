package middleware.ast_nodes.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.BOOL;
import middleware.ExpressionAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.StatementAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class WhileAST extends ForAST {

  public WhileAST(ParserRuleContext ctx, ExpressionAST condition,
      StatementAST body, boolean isDoWhile) {
    super(ctx, new SkipAST(ctx), condition, new SkipAST(ctx), body, isDoWhile);
  }

  public WhileAST(ParserRuleContext ctx, ExpressionAST condition,
      StatementAST statementAST) {
    this(ctx, condition, statementAST, false);
  }
}
