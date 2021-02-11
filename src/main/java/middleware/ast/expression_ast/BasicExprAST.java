package middleware.ast.expression_ast;

import org.antlr.v4.runtime.Token;

public class BasicExprAST extends ExpressionAST{

    public BasicExprAST(Token token) {
        super(token);
    }

    @Override
    public void check() {
        super.check();
    }
}
