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

    IDENTIFIER type = typeAST.getType();
    IDENTIFIER param = ST.lookup(paramName);

    if (type == null) {
      addError(new Undefined(token));
    } else if (!(type instanceof TYPE)) {
      addError(new MismatchedTypes(token, type, new TYPE()));
    } else if (param != null) {
      addError(new DuplicateIdentifier(token));
    } else {
      paramObj = new PARAM((TYPE) type);
      ST.add(paramName, paramObj);
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return null;
  }
}