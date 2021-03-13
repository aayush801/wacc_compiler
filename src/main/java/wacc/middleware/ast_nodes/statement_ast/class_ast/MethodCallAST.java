package wacc.middleware.ast_nodes.statement_ast.class_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.middleware.ast_nodes.function_ast.FunctionCallAST;

public class MethodCallAST extends ExpressionAST {

  private final String objVar;
  private final FunctionCallAST functionCallAST;

  public MethodCallAST(List<WaccError> errors, ParserRuleContext ctx,
      String objVar, FunctionCallAST functionCallAST) {
    super(errors, ctx);
    this.objVar = objVar;
    this.functionCallAST = functionCallAST;
  }

  @Override
  public void check() {
    functionCallAST.check();
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
