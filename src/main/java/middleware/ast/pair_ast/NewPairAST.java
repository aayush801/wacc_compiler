package middleware.ast.pair_ast;

import identifier_objects.basic_types.PAIR;
import middleware.ast.NodeAST;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

public class NewPairAST extends NodeAST {

    private final ExpressionAST fst;
    private final ExpressionAST snd;

    public PAIR pair;

    public NewPairAST(Token token, ExpressionAST fst, ExpressionAST snd) {
        super(token);
        this.fst = fst;
        this.snd = snd;
    }

    public ExpressionAST getFst() {
        return fst;
    }

    public ExpressionAST getSnd() {
        return snd;
    }

    @Override
    public void check() {
        fst.check();
        snd.check();
        pair = new PAIR(fst.getType(), snd.getType());
    }
}
