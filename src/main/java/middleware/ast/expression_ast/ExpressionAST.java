package middleware.ast.expression_ast;

import identifier_objects.TYPE;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

import java.util.Arrays;
import java.util.List;

public class ExpressionAST extends NodeAST {
  protected TYPE type;
  protected final List<String> NUM_BIN_OPS = Arrays.asList("+", "-", "*", "/", "%");
  protected final List<String> NUM_CHAR_BIN_OPS = Arrays.asList(">", "<", ">=", "<=");
  protected final List<String> EXPR_BIN_OPS = Arrays.asList("==", "!=");
  protected final List<String> BOOL_BIN_OPS = Arrays.asList("&&", "||");

  public ExpressionAST(Token token) {
    super(token);
  }

  public TYPE getType() {
    return type;
  }

}
