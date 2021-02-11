package middleware.ast.expression_ast;

import identifier_objects.TYPE;
import org.antlr.v4.runtime.Token;

public class LiteralsAST extends ExpressionAST{

    public LiteralsAST(Token token, TYPE type) {
        super(token);
        this.type = type;
    }

    @Override
    public void check() {

    }
}
