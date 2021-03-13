package wacc.middleware.ast_nodes.statement_ast.class_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.METHOD;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.Visibility;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.TypeAST;
import wacc.middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import wacc.middleware.ast_nodes.function_ast.ParamAST;

public class MethodDeclarationAST extends NodeAST {

  private final FunctionDeclarationAST func;
  private final Visibility visibility;

  public MethodDeclarationAST(List<WaccError> errors, ParserRuleContext ctx,
      FunctionDeclarationAST func, Visibility visibility) {
    super(errors, ctx);
    this.func = func;
    this.visibility = visibility;
  }

  @Override
  public void check() {
    func.check();
  }

  public void checkStatement() {
    func.checkStatement();
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return null;
  }
}
