package wacc.middleware.ast_nodes.class_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.ConstructorError;
import wacc.frontend.identifier_objects.FUNCTION;
import wacc.frontend.identifier_objects.METHOD;
import wacc.frontend.identifier_objects.PARAM;
import wacc.middleware.symbol_table.ClassSymbolTable;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.symbol_table.SymbolTable;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.function_ast.ParamAST;

public class ConstructorAST extends NodeAST {

  private final String className;
  private final NodeASTList<ParamAST> paramASTS;
  private final StatementAST constructorBody;

  private FUNCTION funcobj;

  public ConstructorAST(List<WaccError> errors,
      ParserRuleContext ctx, String className, NodeASTList<ParamAST> paramASTS,
      StatementAST constructorBody) {
    super(errors, ctx);
    this.className = className;
    this.paramASTS = paramASTS;
    this.constructorBody = constructorBody;
  }

  @Override
  public void check() {
    if (!(ST instanceof ClassSymbolTable)) {
      return;
    }

    // if constructor name is not the class name the constructor is not correctly defined
    if(!(((ClassSymbolTable) ST).getClassName().equals(className))){
      addError(new ConstructorError(ctx));
      return;
    }

    //creating new scope
    ST = new SymbolTable(ST);

    funcobj = new FUNCTION(null);
    funcobj.setST(ST);

    //checking each parameter and adding to function's formal list
    for (ParamAST paramAST : paramASTS){
      paramAST.check();
      funcobj.formals.add(paramAST.getParamObj());
    }

    //checking constructor body
    constructorBody.check();

    //resetting scope
    ST = ST.getEncSymTable();

  }

  public FUNCTION getFuncobj() {
    return funcobj;
  }

  public NodeASTList<ParamAST> getParamASTS() {
    return paramASTS;
  }

  public StatementAST getConstructorBody() {
    return constructorBody;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
