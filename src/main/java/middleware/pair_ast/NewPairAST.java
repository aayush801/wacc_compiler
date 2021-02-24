package middleware.pair_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.PAIR;
import java.util.ArrayList;
import java.util.List;
import middleware.ExpressionAST;
import middleware.NodeAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class NewPairAST extends NodeAST {

  private final ExpressionAST fstExpr, sndExpr;
  public PAIR pair;

  public NewPairAST(ParserRuleContext ctx, ExpressionAST fstExpr,
      ExpressionAST sndExpr) {
    super(ctx);
    this.fstExpr = fstExpr;
    this.sndExpr = sndExpr;
  }

  public PAIR getPair() {
    return pair;
  }

  @Override
  public void check() {

    // check both expressions.
    fstExpr.check();
    sndExpr.check();

    boolean error = false;

    // check that boh expressions are of type TYPE.
    // If not, they are a function identifier, which is invalid.
    if (!(fstExpr.getType() instanceof TYPE)) {
      addError(new MismatchedTypes(fstExpr.ctx, fstExpr.getType(), new TYPE()));
      error = true;
    }

    if (!(sndExpr.getType() instanceof TYPE)) {
      addError(new MismatchedTypes(sndExpr.ctx, sndExpr.getType(), new TYPE()));
      error = true;
    }

    if (!error) {
      // If both types valid, make a new pair.
      pair = new PAIR(fstExpr.getType(), sndExpr.getType());
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return new ArrayList<>();
  }

}
