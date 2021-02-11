package middleware.ast.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
import org.antlr.v4.runtime.Token;

public class ReadAST extends StatementAST {

  private LHSAssignAST LHS;

  public ReadAST(Token token, LHSAssignAST LHS) {
    super(token);
    this.LHS = LHS;
  }

  @Override
  public void check() {
    LHS.check();
    IDENTIFIER type = LHS.getType();
    if (type == null) {
      addError(new Undefined(token));
    } else if (!(type instanceof INT || type instanceof CHAR)) {
      addError(new MismatchedTypes(token, type, new INT(), new CHAR()));
    }
  }
}
