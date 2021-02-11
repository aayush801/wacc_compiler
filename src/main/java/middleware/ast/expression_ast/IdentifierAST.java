package middleware.ast.expression_ast;

import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import org.antlr.v4.runtime.Token;

import javax.swing.text.StyledEditorKit;

public class IdentifierAST extends ExpressionAST {

    private final String identifier;

    public IdentifierAST(Token token, String identifier) {
        super(token);
        this.identifier = identifier;
        type = ST.lookup(identifier);
    }

    @Override
    public void check() {
        if (type == null) {
            addError(new Undefined(token, token.toString()));
        }
    }

    @Override
    public boolean isIdent() {
        return true;
    }
}
