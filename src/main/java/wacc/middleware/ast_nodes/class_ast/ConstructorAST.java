package wacc.middleware.ast_nodes.class_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.ConstructorError;
import wacc.middleware.symbol_table.ClassSymbolTable;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.symbol_table.SymbolTable;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.function_ast.ParamAST;

public class ConstructorAST extends NodeAST {

  String className;
  NodeASTList<ParamAST> paramASTS;
  StatementAST constructorBody;

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

    if (!className.equals(((ClassSymbolTable) ST).getClassName())) {
      addError(new ConstructorError(ctx));
      return;
    }

    ST = new SymbolTable(ST);
    paramASTS.check();
    constructorBody.check();
    ST = ST.getEncSymTable();

  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
