package middleware.ast.statement_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.basic_types.PAIR;
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
        IDENTIFIER type = expr.getType();
        if(type == null) addError(new Undefined(token));
        else if(!(type instanceof PAIR)) addError(new MismatchedTypes(token, type, new PAIR()));
    }
}
