package errors.semantic_errors;

import identifier_objects.IDENTIFIER;
import org.antlr.v4.runtime.Token;

public class expressionNotFound extends WaccSemanticError{

    private final IDENTIFIER actual;

    public expressionNotFound(Token token, IDENTIFIER actual) {
        super(token);
        this.actual = actual;
    }

    @Override
    public String getErrorMessage() {
        return "Expected an expression" +
                ", but got type : " + ((actual == null) ? "null" : actual.toString().toUpperCase());
    }

}
