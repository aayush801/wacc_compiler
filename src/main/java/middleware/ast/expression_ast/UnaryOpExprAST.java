package middleware.ast.expression_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.expressionNotFound;

import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.basic_types.ARRAY;
import identifier_objects.basic_types.BOOL;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
import identifier_objects.polymorhpic_types.EXPR;
import org.antlr.v4.runtime.Token;

import java.util.HashMap;
import java.util.Map;

public class UnaryOpExprAST extends ExpressionAST {

  private final ExpressionAST right;
  private final String operator;

  protected final Map<String, TYPE> unopsInputType = new HashMap<>() {{
    put("-", new INT());
    put("!", new BOOL());
    put("ord", new CHAR());
    put("chr", new INT());
    put("len", new ARRAY(new EXPR()));
  }};

  protected final Map<String, TYPE> unopsOutputType = new HashMap<>() {{
    put("-", new INT());
    put("!", new BOOL());
    put("ord", new INT());
    put("chr", new CHAR());
    put("len", new INT());
  }};

  public UnaryOpExprAST(Token token, ExpressionAST right, String operator) {
    super(token);
    this.right = right;
    this.operator = operator;
  }

  @Override
  public void check() {
    right.check();
    IDENTIFIER rightType = right.getType();

    if(!(rightType instanceof TYPE)){
      addError(new expressionNotFound(token,rightType));
      return ;
    }

    switch (operator) {
      case "!": {
        boolean rightBool = rightType instanceof BOOL;
        if (!rightBool) {
          addError(new MismatchedTypes(token, rightType, new BOOL()));
        }
        type = new BOOL();
        break;
      }
      case "-": {
        boolean rightInt = rightType instanceof INT;
        if (!rightInt) {
          addError(new MismatchedTypes(token, rightType, new INT()));
        }
        type = new INT();
        break;
      }
      case "len": {
        boolean rightArray = rightType instanceof ARRAY;
        if (!rightArray) {
          addError(new MismatchedTypes(token, rightType, new ARRAY(new EXPR())));
        }
        type = new INT();
        break;
      }
      case "chr": {
        boolean rightInt = rightType instanceof INT;
        if (!rightInt) {
          addError(new MismatchedTypes(token, rightType, new INT()));
        }
        type = new CHAR();
        break;
      }
      default: {
        // must be ord.
        boolean rightChar = rightType instanceof CHAR;
        if (!rightChar) {
          addError(new MismatchedTypes(token, rightType, new CHAR()));
        }
        type = new INT();
      }
    }

  }
}
