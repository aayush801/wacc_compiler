package middleware.ast.expression_ast;

import org.antlr.v4.runtime.Token;

public class ExprInBracketsAST extends ExpressionAST {

    private final ExpressionAST expr;

    public ExprInBracketsAST(Token token, ExpressionAST expr) {
        super(token);
        this.expr = expr;
    }
}
