package middleware.ast.expression_ast;

import org.antlr.v4.runtime.Token;

public class UnaryOpExprAST extends ExpressionAST {
    private final ExpressionAST right;
    private final String operator;

    public UnaryOpExprAST(Token token, ExpressionAST right, String operator) {
        super(token);
        this.right = right;
        this.operator = operator;
    }

    @Override
    public void check() {
        right.check();
    }
}
