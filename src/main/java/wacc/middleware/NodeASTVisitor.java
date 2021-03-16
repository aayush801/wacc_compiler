package wacc.middleware;

import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.arrays_ast.ArrayAST;
import wacc.middleware.ast_nodes.arrays_ast.ArrayElemAST;
import wacc.middleware.ast_nodes.class_ast.FieldAST;
import wacc.middleware.ast_nodes.class_ast.MethodCallAST;
import wacc.middleware.ast_nodes.class_ast.MethodDeclarationAST;
import wacc.middleware.ast_nodes.class_ast.NewObjectAST;
import wacc.middleware.ast_nodes.expression_ast.BinOpExprAST;
import wacc.middleware.ast_nodes.expression_ast.IdentifierAST;
import wacc.middleware.ast_nodes.expression_ast.LiteralsAST;
import wacc.middleware.ast_nodes.expression_ast.SizeOfAST;
import wacc.middleware.ast_nodes.expression_ast.UnaryOpExprAST;
import wacc.middleware.ast_nodes.function_ast.FunctionCallAST;
import wacc.middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import wacc.middleware.ast_nodes.function_ast.ParamAST;
import wacc.middleware.ast_nodes.pair_ast.NewPairAST;
import wacc.middleware.ast_nodes.pair_ast.PairElemAST;
import wacc.middleware.ast_nodes.pointers_ast.PointerElemAST;
import wacc.middleware.ast_nodes.prog_ast.ProgAST;
import wacc.middleware.ast_nodes.statement_ast.AssignmentAST;
import wacc.middleware.ast_nodes.statement_ast.BeginAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.BreakAST;
import wacc.middleware.ast_nodes.statement_ast.ChainedStatementAST;
import wacc.middleware.ast_nodes.class_ast.ClassDefinitionAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.ContinueAST;
import wacc.middleware.ast_nodes.statement_ast.ExitAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.ForAST;
import wacc.middleware.ast_nodes.statement_ast.FreeAST;
import wacc.middleware.ast_nodes.statement_ast.IfElseAST;
import wacc.middleware.ast_nodes.statement_ast.LHSAssignAST;
import wacc.middleware.ast_nodes.statement_ast.PrintAST;
import wacc.middleware.ast_nodes.statement_ast.RHSAssignAST;
import wacc.middleware.ast_nodes.statement_ast.ReadAST;
import wacc.middleware.ast_nodes.statement_ast.ReturnAST;
import wacc.middleware.ast_nodes.statement_ast.SkipAST;
import wacc.middleware.ast_nodes.statement_ast.VariableDeclarationAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.WhileAST;
import wacc.middleware.ast_nodes.types_ast.ArrayTypeAST;
import wacc.middleware.ast_nodes.types_ast.BaseTypeAST;
import wacc.middleware.ast_nodes.types_ast.PairElemTypeAST;
import wacc.middleware.ast_nodes.types_ast.PairTypeAST;
import wacc.middleware.ast_nodes.types_ast.PointerTypeAST;

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

  public abstract T visit(SizeOfAST sizeOf);

  public abstract T visit(VariableDeclarationAST variableDeclaration);

  public abstract T visit(ForAST forLoop);

  public abstract T visit(WhileAST whileLoop);

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

  public abstract  T visit(PointerTypeAST pointerType);

  public abstract  T visit(PointerElemAST pointerElem);

  public abstract T visit(ClassDefinitionAST classDef);

  public abstract T visit(NewObjectAST newObjectAST);

  public abstract T visit(MethodCallAST classDef);

  public abstract T visit(MethodDeclarationAST classDef);

  public abstract T visit(FieldAST fields);
}
