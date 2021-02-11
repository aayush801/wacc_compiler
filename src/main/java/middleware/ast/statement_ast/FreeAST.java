package middleware.ast.statement_ast;

import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class FreeAST extends StatementAST {

    private final ExpressionAST expr;

    public FreeAST(Token token, ExpressionAST expr) {
        super(token);
        this.expr = expr;
    }

    @Override
    public void check() {
        expr.check();
    }
}
