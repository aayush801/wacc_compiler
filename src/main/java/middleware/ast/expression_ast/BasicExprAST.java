package middleware.ast.expression_ast;

import identifier_objects.TYPE;
import org.antlr.v4.runtime.Token;

public class BasicExprAST extends ExpressionAST{

    public BasicExprAST(Token token, TYPE type) {
        super(token);
        this.type = type;
    }

    @Override
    public void check() {

    }
}
