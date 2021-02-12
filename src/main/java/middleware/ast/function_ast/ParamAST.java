package middleware.ast.function_ast;

import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.PARAM;
import identifier_objects.TYPE;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.NodeAST;
import middleware.ast.types_ast.TypeAST;
import org.antlr.v4.runtime.Token;

public class ParamAST extends NodeAST {

  PARAM paramObj;
  private TypeAST typeAST;
  private String paramName;

  public ParamAST(Token token, TypeAST typeAST, String paramName) {
    super(token);
    this.typeAST = typeAST;
    this.paramName = paramName;
  }

  @Override
  public void check() {
    typeAST.check();

    IDENTIFIER type = typeAST.getType();
    IDENTIFIER param = ST.lookup(paramName);

    if (type == null) {
      addError(new Undefined(token));
    } else if (!(type instanceof TYPE)) {
      addError(new MismatchedTypes(token, type, new EXPR()));
    } else if (param != null) {
      addError(new DuplicateIdentifier(token));
    } else {
      paramObj = new PARAM((TYPE) type);
      ST.add(paramName, paramObj);
    }
  }
}
