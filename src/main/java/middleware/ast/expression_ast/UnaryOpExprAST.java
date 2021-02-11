package middleware.ast.expression_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.basic_types.ARRAY;
import identifier_objects.basic_types.BOOL;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
import identifier_objects.polymorhpic_types.EXPR;
import org.antlr.v4.runtime.Token;

import java.util.HashMap;
import java.util.Map;

public class UnaryOpExprAST extends ExpressionAST {
    private final ExpressionAST right;
    private final String operator;

    protected final Map<String, TYPE> unopsInputType = new HashMap<>()
    {{
        put("-", new INT());
        put("!", new BOOL());
        put("ord", new CHAR());
        put("chr", new INT());
        put("len", new ARRAY(new EXPR()));
    }};

    protected final Map<String, TYPE> unopsOutputType = new HashMap<> ()
    {{
        put("-", new INT());
        put("!", new BOOL());
        put("ord", new INT());
        put("chr", new CHAR());
        put("len", new INT());
    }};

    public UnaryOpExprAST(Token token, ExpressionAST right, String operator) {
        super(token);
        this.right = right;
        this.operator = operator;
    }

    @Override
    public void check() {
        right.check();
        IDENTIFIER righttype = right.getType();

        switch(operator) {
            case "!": {
                boolean rightBool = righttype instanceof BOOL;
                if(!rightBool) {
                    addError(new MismatchedTypes(token, righttype, new BOOL()));
                }
                type = new BOOL();
                break;
            }
            case "-": {
                boolean rightInt = righttype instanceof INT;
                if(!rightInt) {
                    addError(new MismatchedTypes(token, righttype, new INT()));
                }
                type = new INT();
                break;
            }
            case "len": {
                boolean rightArray = righttype instanceof ARRAY;
                if(!rightArray) {
                    addError(new MismatchedTypes(token, righttype, new ARRAY(new EXPR())));
                }
                type = new INT();
                break;
            }
            case "chr": {
                boolean rightInt = righttype instanceof INT;
                if(!rightInt) {
                    addError(new MismatchedTypes(token, righttype, new INT()));
                }
                type = new CHAR();
                break;
            }
            default: {
                // must be ord.
                boolean rightChar = righttype instanceof CHAR;
                if(!rightChar) {
                    addError(new MismatchedTypes(token, righttype, new CHAR()));
                }
                type = new INT();
            }
        }

    }
}