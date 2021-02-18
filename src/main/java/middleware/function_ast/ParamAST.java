package middleware.function_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.TYPE;
import java.util.List;
import middleware.NodeAST;
import middleware.types_ast.TypeAST;
import org.antlr.v4.runtime.Token;

public class ParamAST extends NodeAST {

  private final TypeAST typeAST;
  private final String paramName;
  PARAM paramObj;

  public ParamAST(Token token, TypeAST typeAST, String paramName) {
    super(token);
    this.typeAST = typeAST;
    this.paramName = paramName;
  }

  @Override
  public void check() {
    typeAST.check();

    TYPE type = typeAST.getType();
    IDENTIFIER param = ST.lookup(paramName);

    if (type != null) {
      if (param != null) {
        addError(new DuplicateIdentifier(token));
      } else {
        paramObj = new PARAM(type);
        ST.add(paramName, paramObj);
      }
    }

  }

}
