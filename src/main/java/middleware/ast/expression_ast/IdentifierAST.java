package middleware.ast.expression_ast;

import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import org.antlr.v4.runtime.Token;

public class IdentifierAST extends ExpressionAST {

    private final String identifier;

    public IdentifierAST(Token token, String identifier) {
        super(token);
        this.identifier = identifier;
        type = calcType();
    }

    public IdentifierAST(Token token, String identifier, TYPE type) {
        super(token);
        this.identifier = identifier;
        this.type = type;
    }

    private TYPE calcType() {
        IDENTIFIER ident = ST.lookup(identifier);

        if (ident instanceof TYPE) {
            return (TYPE) ident;
        } else {
            return null;
        }
    }
}
