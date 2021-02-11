package middleware.ast;

import java.util.List;
import middleware.ast.function_ast.FunctionDeclarationAST;
import middleware.ast.statement_ast.StatementAST;
import org.antlr.v4.runtime.Token;

public class ProgAST extends NodeAST {
private List<FunctionDeclarationAST> functionDeclarationASTS;
private StatementAST statementAST;

  public ProgAST(Token token, List<FunctionDeclarationAST> functionDeclarationASTS, StatementAST statementAST) {
    super(token);
    this.functionDeclarationASTS = functionDeclarationASTS;
    this.statementAST = statementAST;
  }

  @Override
  public void check() {
    for (FunctionDeclarationAST funcs : functionDeclarationASTS){
      funcs.check();
    }

    for (FunctionDeclarationAST funcs : functionDeclarationASTS){
      funcs.checkStatement();
    }

    statementAST.check();
  }
}
