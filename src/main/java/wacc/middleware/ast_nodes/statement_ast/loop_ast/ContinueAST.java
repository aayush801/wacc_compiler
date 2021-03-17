package wacc.middleware.ast_nodes.statement_ast.loop_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.OutsideLoop;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class ContinueAST extends NodeAST {

  public ContinueAST(List<WaccError> errors, ParserRuleContext ctx) {
    super(errors, ctx);
  }

  @Override
  public void check() {
    if (insideLoops == 0) {
      addError(new OutsideLoop(ctx, "continue"));
    }
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return null;
  }
}
