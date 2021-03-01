package backend;

import backend.instructions.Instruction;
import java.util.List;
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
import middleware.statement_ast.AssignmentAST;
import middleware.statement_ast.BeginAST;
import middleware.statement_ast.ChainedStatementAST;
import middleware.statement_ast.ExitAST;
import middleware.statement_ast.FreeAST;
import middleware.statement_ast.IfElseAST;
import middleware.statement_ast.PrintAST;
import middleware.statement_ast.RHSAssignAST;
import middleware.statement_ast.ReadAST;
import middleware.statement_ast.ReturnAST;
import middleware.statement_ast.SkipAST;
import middleware.statement_ast.VariableDeclarationAST;
import middleware.statement_ast.WhileAST;

public class WaccCodeGeneratorVisitor extends NodeASTVisitor<List<Instruction>> {

  @Override
  public List<Instruction> visit(ProgAST prog) {
    return null;
  }

  @Override
  public List<Instruction> visit(ArrayAST array) {
    return null;
  }

  @Override
  public List<Instruction> visit(ArrayElemAST arrayElem) {
    return null;
  }

  @Override
  public List<Instruction> visit(BinOpExprAST binOpExpr) {
    return null;
  }

  @Override
  public List<Instruction> visit(IdentifierAST identifier) {
    return null;
  }

  @Override
  public List<Instruction> visit(LiteralsAST literal) {
    return null;
  }

  @Override
  public List<Instruction> visit(UnaryOpExprAST unaryOpExpr) {
    return null;
  }

  @Override
  public List<Instruction> visit(FunctionDeclarationAST functionDeclaration) {
    return null;
  }

  @Override
  public List<Instruction> visit(FunctionCallAST functionCall) {
    return null;
  }

  @Override
  public List<Instruction> visit(ParamAST param) {
    return null;
  }

  @Override
  public List<Instruction> visit(NewPairAST newPair) {
    return null;
  }

  @Override
  public List<Instruction> visit(AssignmentAST assignment) {
    return null;
  }

  @Override
  public List<Instruction> visit(BeginAST begin) {
    return null;
  }

  @Override
  public List<Instruction> visit(ChainedStatementAST chainedStatement) {
    return null;
  }

  @Override
  public List<Instruction> visit(ExitAST exit) {
    return null;
  }

  @Override
  public List<Instruction> visit(FreeAST free) {
    return null;
  }

  @Override
  public List<Instruction> visit(IfElseAST ifElse) {
    return null;
  }

  @Override
  public List<Instruction> visit(PrintAST print) {
    return null;
  }

  @Override
  public List<Instruction> visit(ReadAST read) {
    return null;
  }

  @Override
  public List<Instruction> visit(ReturnAST returnStatement) {
    return null;
  }

  @Override
  public List<Instruction> visit(RHSAssignAST rhs) {
    return null;
  }

  @Override
  public List<Instruction> visit(SkipAST skip) {
    return null;
  }

  @Override
  public List<Instruction> visit(VariableDeclarationAST variableDeclaration) {
    return null;
  }

  @Override
  public List<Instruction> visit(WhileAST whileLoop) {
    return null;
  }
}
