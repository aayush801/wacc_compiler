package middleware.ast.expression_ast;

import org.antlr.v4.runtime.Token;

public class BinOpExprAST extends ExpressionAST {
    private final ExpressionAST left;
    private final ExpressionAST right;
    private final String operator;

    public BinOpExprAST(Token token, ExpressionAST left, String operator, ExpressionAST right) {
        super(token);
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public void check() {
        left.check();
        right.check();
    }
}
