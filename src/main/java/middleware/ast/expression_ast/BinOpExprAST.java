package middleware.ast.expression_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.IDENTIFIER;
import identifier_objects.basic_types.*;
import org.antlr.v4.runtime.Token;

import java.util.Arrays;
import java.util.List;

public class BinOpExprAST extends ExpressionAST {

    protected final List<String> NUM_BIN_OPS = Arrays.asList("+", "-", "*", "/", "%");
    protected final List<String> NUM_CHAR_BIN_OPS = Arrays.asList(">", "<", ">=", "<=");
    protected final List<String> EXPR_BIN_OPS = Arrays.asList("==", "!=");

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

        IDENTIFIER lefttype = left.getType();
        IDENTIFIER righttype = right.getType();

        if(NUM_BIN_OPS.contains(operator)) {
            boolean leftInt = lefttype instanceof INT;
            boolean rightInt = righttype instanceof INT;
            if (!leftInt) {
                addError(new MismatchedTypes(token, lefttype, new INT()));
            }
            if (!rightInt) {
                addError(new MismatchedTypes(token, righttype, new INT()));
            }
            type = new INT();
            return;
        }

        if(NUM_CHAR_BIN_OPS.contains(operator)) {
            boolean leftIntOrChar = lefttype instanceof INT || lefttype instanceof CHAR;
            boolean rightIntOrChar = righttype instanceof INT || righttype instanceof CHAR;
            if(!leftIntOrChar) {
                addError(new MismatchedTypes(token, lefttype, new INT(), new CHAR()));
            }
            if (!rightIntOrChar) {
                addError(new MismatchedTypes(token, righttype, new INT(), new CHAR()));
            }
            if (!isCompatible(lefttype, righttype)) {
                addError(new MismatchedTypes(token, righttype, lefttype));
            }
            type = new BOOL();
            return;
        }

        if(EXPR_BIN_OPS.contains(operator)) {
            boolean sameType = isCompatible(lefttype, righttype);
            type = new BOOL();
            return;
        }

        boolean leftBool = lefttype instanceof BOOL;
        boolean rightBool = righttype instanceof BOOL;
        if (!leftBool) {
            addError(new MismatchedTypes(token, new BOOL(), lefttype));
        }
        if (!rightBool) {
            addError(new MismatchedTypes(token, new BOOL(), righttype));
        }
        type = new BOOL();
    }

}
