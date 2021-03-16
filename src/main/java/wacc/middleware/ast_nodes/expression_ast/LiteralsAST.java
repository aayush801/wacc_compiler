package wacc.middleware.ast_nodes.expression_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.basic_types.BOOL;
import wacc.frontend.identifier_objects.basic_types.CHAR;
import wacc.frontend.identifier_objects.basic_types.INT;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class LiteralsAST extends ExpressionAST {

  private final TYPE type;
  private String text;

  public LiteralsAST(List<WaccError> errors, ParserRuleContext ctx,
      String text, TYPE type) {
    super(errors, ctx);
    this.type = type;
    this.text = text;
  }

  public LiteralsAST(List<WaccError> errors, ParserRuleContext ctx, Integer n) {
    this(errors, ctx, n.toString(), new INT());
  }

  public LiteralsAST(List<WaccError> errors, ParserRuleContext ctx, Boolean b) {
    this(errors, ctx, b.toString(), new BOOL());
  }

  public LiteralsAST(List<WaccError> errors, ParserRuleContext ctx, Character c) {
    this(errors, ctx, c.toString(), new CHAR());
  }

  public LiteralsAST(List<WaccError> errors, ParserRuleContext ctx, TYPE type) {
    this(errors, ctx, ctx.getText(), type);
  }

  public String getText() {
    return text;
  }

  @Override
  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }


}
