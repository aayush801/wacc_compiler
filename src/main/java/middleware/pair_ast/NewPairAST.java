package middleware.pair_ast;

import backend.NodeASTVisitor;
import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.Move;
import backend.instructions.Store;
import backend.instructions.addr_modes.ImmediateAddress;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.instructions.addr_modes.ZeroOffset;
import backend.operands.ImmediateNum;
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
  private PAIR pair;

  public NewPairAST(ParserRuleContext ctx, ExpressionAST fstExpr,
      ExpressionAST sndExpr) {
    super(ctx);
    this.fstExpr = fstExpr;
    this.sndExpr = sndExpr;
  }

  public PAIR getPair() {
    return pair;
  }

  public ExpressionAST getFstExpr() {
    return fstExpr;
  }

  public ExpressionAST getSndExpr() {
    return sndExpr;
  }


  @Override
  public void check() {

    // check both expressions.
    fstExpr.check();
    sndExpr.check();

    TYPE fstType = fstExpr.getType();
    TYPE sndType = sndExpr.getType();

    boolean error = false;

    // check that boh expressions are of type TYPE.
    // If not, they are a function identifier, which is invalid.
    if (fstType == null) {
      addError(
          new MismatchedTypes(fstExpr.ctx, fstType, new TYPE()));
      error = true;
    }


    if (sndType == null) {
      addError(
          new MismatchedTypes(sndExpr.ctx, sndType, new TYPE()));
      error = true;
    }

    if (!error) {
      // If both types valid, make a new pair.
      pair = new PAIR(fstType, sndType);
    }

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    List<Instruction> instructions = new ArrayList<>();

    // Allocate memory for two address, one for each element of the pair
    instructions.add(new Load(Register.R0, new ImmediateAddress(8)));
    instructions.add(new Branch("malloc", true));
    Register pairAddress = registers.get(0);

    // Copy the address to the first element of the pair
    instructions.add(new Move(pairAddress, Register.R0));
    List<Register> remaining = new ArrayList<>(registers);
    remaining.remove(0);

    Register pairElem = remaining.get(0);
    instructions.addAll(fstExpr.translate(remaining));

    // Allocate memory for first element of the pair based on the size of the type
    instructions.add(new Load(Register.R0,
        new ImmediateAddress(fstExpr.getType().getSize())));

    instructions.add(new Branch("malloc", true));

    // Store the value of the first element at the given address
    instructions.add(new Store(pairElem, new ZeroOffset(Register.R0),
        pair.getFirst().getSize()));

    // Store the address pf the first element into the first word of the pair
    instructions.add(new Store(Register.R0, new ZeroOffset(pairAddress)));

    // Repeating same for second element
    instructions.addAll(sndExpr.translate(remaining));
    instructions.add(new Load(Register.R0,
        new ImmediateAddress(sndExpr.getType().getSize())));

    instructions.add(new Branch("malloc", true));
    instructions.add(new Store(pairElem, new ZeroOffset(Register.R0),
        pair.getSecond().getSize()));

    // Storing address to value of second element in the second word of pair
    instructions.add(
        new Store(Register.R0,
            new ImmediateOffset(pairAddress, new ImmediateNum(4))));

    return instructions;
  }

  @Override
  public List<Instruction> accept(NodeASTVisitor visitor) {
    return (List<Instruction>) visitor.visit(this);

  }

}
