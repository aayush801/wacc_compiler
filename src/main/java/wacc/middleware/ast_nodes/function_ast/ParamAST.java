package wacc.middleware.ast_nodes.function_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.DuplicateIdentifier;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.PARAM;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.TypeAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ParamAST extends NodeAST {

  private final TypeAST typeAST;
  private final String paramName;
  private PARAM paramObj;

  public ParamAST(List<WaccError> errors,ParserRuleContext ctx, TypeAST typeAST,
      String paramName) {
    super(errors, ctx);
    this.typeAST = typeAST;
    this.paramName = paramName;
  }

  public PARAM getParamObj() {
    return paramObj;
  }

  @Override
  public void check() {
    typeAST.check();

    TYPE type = typeAST.getType();
    IDENTIFIER param = ST.lookup(paramName);

    if (type != null) {
      if (param != null) {
        addError(new DuplicateIdentifier(ctx));
      } else {
        paramObj = new PARAM(type);
        ST.add(paramName, paramObj);
      }
    }
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
