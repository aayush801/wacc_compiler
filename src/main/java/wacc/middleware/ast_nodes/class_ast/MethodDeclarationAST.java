package wacc.middleware.ast_nodes.class_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.NotAMethod;
import wacc.errors.semantic_errors.WaccSemanticError;
import wacc.frontend.identifier_objects.METHOD;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.Visibility;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.TypeAST;
import wacc.middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import wacc.middleware.ast_nodes.function_ast.ParamAST;
import wacc.middleware.symbol_table.ClassSymbolTable;

public class MethodDeclarationAST extends FunctionDeclarationAST {

  private final Visibility visibility;

  public MethodDeclarationAST(List<WaccError> errors, ParserRuleContext ctx, Visibility visibility,
      TypeAST typeAST, String funcName, NodeASTList<ParamAST> paramASTList,
      StatementAST statementAST) {
    super(errors, ctx, typeAST, funcName, paramASTList, statementAST);
    this.visibility = visibility;
  }

  @Override
  public void check() {
    if(!(ST instanceof ClassSymbolTable)){
      addError(new WaccSemanticError(ctx) {
        @Override
        public String getErrorMessage() {
          return "Unhandled error in method declaration";
        }
      });
      return;
    }

    // if method has same name as constructor
    if(getBaseName().equals(((ClassSymbolTable) ST).getClassName())){
      addError(new NotAMethod(ctx, getBaseName()));
      return;
    }

    //calls functionDeclarationAST check
    super.check();

    METHOD methodObj = new METHOD(funcObj, visibility);
    ST.add(funcObj.getName(), methodObj);
  }

  public Visibility getVisibility() {
    return visibility;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
