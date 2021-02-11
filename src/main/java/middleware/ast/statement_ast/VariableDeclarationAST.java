package middleware.ast.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import identifier_objects.polymorhpic_types.EXPR;
import org.antlr.v4.runtime.Token;

public class VariableDeclarationAST extends StatementAST {

  private final String typename;
  private final String varname;
  VARIABLE varObj;

  public VariableDeclarationAST(Token token, String typename, String varname) {
    super(token);
    this.typename = typename;
    this.varname = varname;
  }

  @Override
  public void check() {
    IDENTIFIER type = ST.lookupAll(typename);
    IDENTIFIER variable = ST.lookup(varname);
    if (type == null) addError(new Undefined(token, typename));
    else if (!(type instanceof TYPE)) addError(new MismatchedTypes(token, type, new EXPR()));
    else if (variable == null) addError(new Undefined(token, varname));
    else varObj = new VARIABLE((TYPE) type);
    ST.add(varname, varObj);
  }
}
