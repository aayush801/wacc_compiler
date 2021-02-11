package middleware.ast.pair_ast;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.TYPE;
import identifier_objects.basic_types.PAIR;
import middleware.ast.NodeAST;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class PairElemAST extends NodeAST {

    TYPE type;
    private final ExpressionAST expr;
    int index;

    public PairElemAST(Token token, ExpressionAST expr, int index) {
        super(token);
        this.expr = expr;
        this.index = index;
    }

    @Override
    public void check() {
        TYPE exprtype = expr.getType();
        if (!(exprtype instanceof PAIR)) {
            addError(new MismatchedTypes(token, exprtype, new PAIR()));
        } else {
            PAIR pair = (PAIR) exprtype;
            if (index == 0) {
                type = pair.getFirst();
            } else {
                type = pair.getSecond();
            }
        }

    }
}
