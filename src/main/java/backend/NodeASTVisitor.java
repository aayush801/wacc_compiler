package backend;

import middleware.NodeAST;
import middleware.NodeASTList;
import middleware.ProgAST;
import middleware.arrays_ast.ArrayAST;
import middleware.arrays_ast.ArrayElemAST;
import middleware.expression_ast.BinOpExprAST;
import middleware.expression_ast.IdentifierAST;
import middleware.expression_ast.LiteralsAST;
import middleware.expression_ast.UnaryOpExprAST;
import middleware.function_ast.FunctionCallAST;
import middleware.function_ast.FunctionDeclarationAST;
import middleware.function_ast.ParamAST;
import middleware.pair_ast.NewPairAST;
import middleware.pair_ast.PairElemAST;
import middleware.statement_ast.AssignmentAST;
import middleware.statement_ast.BeginAST;
import middleware.statement_ast.ChainedStatementAST;
import middleware.statement_ast.ExitAST;
import middleware.statement_ast.FreeAST;
import middleware.statement_ast.IfElseAST;
import middleware.statement_ast.LHSAssignAST;
import middleware.statement_ast.PrintAST;
import middleware.statement_ast.RHSAssignAST;
import middleware.statement_ast.ReadAST;
import middleware.statement_ast.ReturnAST;
import middleware.statement_ast.SkipAST;
import middleware.statement_ast.VariableDeclarationAST;
import middleware.statement_ast.WhileAST;
import middleware.types_ast.ArrayTypeAST;
import middleware.types_ast.BaseTypeAST;
import middleware.types_ast.PairElemTypeAST;
import middleware.types_ast.PairTypeAST;

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

  public abstract T visit(WhileAST whileLoop);

  public abstract T visit(NodeASTList<NodeAST> nodeList);

  public T visit(NodeAST node) {
    return node.accept(this);
  }

  public abstract T visit(BaseTypeAST baseType);

  public abstract T visit(ArrayTypeAST arrayType);

  public abstract T visit(PairTypeAST pairType);

  public abstract T visit(PairElemTypeAST pairElemType);
}
