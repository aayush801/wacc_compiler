package middleware.ast.expression_ast;

import identifier_objects.TYPE;
import middleware.ast.AbstractSyntaxTree;

public class ExpressionAST extends AbstractSyntaxTree {
    private TYPE type;
    private ExpressionAST left;
    private ExpressionAST right;
    private String operator;

    // base type, like an INT, etc.
    public ExpressionAST(TYPE type) {
        this.type = type;
    }

    // For an expression that is a binOp
    public ExpressionAST(TYPE type, ExpressionAST left, String operator, ExpressionAST right){
        this.type = type;
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    // For an expression that is a unOp
    public ExpressionAST(TYPE type, String operator, ExpressionAST expr){
        this.type = type;
        this.operator = operator;
        right = expr;
    }
}
