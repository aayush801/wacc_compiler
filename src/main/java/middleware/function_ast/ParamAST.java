package middleware.function_ast;

import backend.NodeASTVisitor;
import errors.semantic_errors.DuplicateIdentifier;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.TYPE;
import middleware.NodeAST;
import middleware.TypeAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ParamAST extends NodeAST {

  private final TypeAST typeAST;
  private final String paramName;
  private PARAM paramObj;

  public ParamAST(ParserRuleContext ctx, TypeAST typeAST, String paramName) {
    super(ctx);
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
