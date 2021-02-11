package middleware.ast.arrays_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

public class BaseTypeAST extends TypeAST {

  private TYPE type;
  String typename;

  public BaseTypeAST(Token token, String typename) {
    super(token);
    this.typename = typename;
  }

  @Override
  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    IDENTIFIER identifier = ST.lookupAll(typename);
    if(identifier == null){
      addError(new Undefined(token));
    }else if(!(identifier instanceof TYPE)){
      addError(new MismatchedTypes(token, identifier, new EXPR()));
    }else{
      type = (TYPE) identifier;
    }
  }
}
