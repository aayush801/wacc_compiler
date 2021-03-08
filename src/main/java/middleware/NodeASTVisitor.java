package middleware;

import middleware.ast_nodes.NodeASTList;
import middleware.ast_nodes.arrays_ast.ArrayAST;
import middleware.ast_nodes.arrays_ast.ArrayElemAST;
import middleware.ast_nodes.expression_ast.BinOpExprAST;
import middleware.ast_nodes.expression_ast.IdentifierAST;
import middleware.ast_nodes.expression_ast.LiteralsAST;
import middleware.ast_nodes.expression_ast.UnaryOpExprAST;
import middleware.ast_nodes.function_ast.FunctionCallAST;
import middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import middleware.ast_nodes.function_ast.ParamAST;
import middleware.ast_nodes.pair_ast.NewPairAST;
import middleware.ast_nodes.pair_ast.PairElemAST;
import middleware.ast_nodes.prog_ast.ProgAST;
import middleware.ast_nodes.statement_ast.AssignmentAST;
import middleware.ast_nodes.statement_ast.BeginAST;
import middleware.ast_nodes.statement_ast.BreakAST;
import middleware.ast_nodes.statement_ast.ChainedStatementAST;
import middleware.ast_nodes.statement_ast.ContinueAST;
import middleware.ast_nodes.statement_ast.ExitAST;
import middleware.ast_nodes.statement_ast.ForAST;
import middleware.ast_nodes.statement_ast.FreeAST;
import middleware.ast_nodes.statement_ast.IfElseAST;
import middleware.ast_nodes.statement_ast.LHSAssignAST;
import middleware.ast_nodes.statement_ast.PrintAST;
import middleware.ast_nodes.statement_ast.RHSAssignAST;
import middleware.ast_nodes.statement_ast.ReadAST;
import middleware.ast_nodes.statement_ast.ReturnAST;
import middleware.ast_nodes.statement_ast.SkipAST;
import middleware.ast_nodes.statement_ast.VariableDeclarationAST;
import middleware.ast_nodes.statement_ast.WhileAST;
import middleware.ast_nodes.types_ast.ArrayTypeAST;
import middleware.ast_nodes.types_ast.BaseTypeAST;
import middleware.ast_nodes.types_ast.PairElemTypeAST;
import middleware.ast_nodes.types_ast.PairTypeAST;

public abstract class NodeASTVisitor<T> {

  public abstract T visit(ProgAST prog);

  public abstract T visit(ArrayAST array);

  public abstract T visit(ArrayElemAST arrayElem);

  public abstract T visit(BinOpExprAST binOpExpr);

  public abstract T visit(IdentifierAST identifier);

  public abstract T visit(LiteralsAST literal);

  public abstract T visit(UnaryOpExprAST unaryOpExpr);

  public abstract T visit(FunctionDeclarationAST functionDeclaration);

  public abstract T visit(FunctionCallAST functionCall);

  public abstract T visit(ParamAST param);

  public abstract T visit(NewPairAST newPair);

  public abstract T visit(PairElemAST pairElem);

  public abstract T visit(AssignmentAST assignment);

  public abstract T visit(BeginAST begin);

  public abstract T visit(ChainedStatementAST chainedStatement);

  public abstract T visit(ExitAST exit);

  public abstract T visit(FreeAST free);

  public abstract T visit(IfElseAST ifElse);

  public abstract T visit(PrintAST print);

  public abstract T visit(ReadAST read);

  public abstract T visit(ReturnAST returnStatement);

  public abstract T visit(LHSAssignAST lhs);

  public abstract T visit(RHSAssignAST rhs);

  public abstract T visit(SkipAST skip);

  public abstract T visit(VariableDeclarationAST variableDeclaration);

  public abstract T visit(ForAST forLoop);

  public abstract T visit(ContinueAST forLoop);

  public abstract T visit(BreakAST forLoop);

  public abstract T visit(NodeASTList<NodeAST> nodeList);

  public T visit(NodeAST node) {
    return node.accept(this);
  }

  public abstract T visit(BaseTypeAST baseType);

  public abstract T visit(ArrayTypeAST arrayType);

  public abstract T visit(PairTypeAST pairType);

  public abstract T visit(PairElemTypeAST pairElemType);
}
