package middleware.statement_ast;

import backend.NodeASTVisitor;
import backend.instructions.Branch;
import backend.instructions.Instruction;
import backend.instructions.Move;
import backend.primitive_functions.FreeFunction;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.basic_types.PAIR;
import java.util.ArrayList;
import java.util.List;
import middleware.ExpressionAST;
import middleware.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class FreeAST extends StatementAST {

  private final ExpressionAST expr;

  public FreeAST(ParserRuleContext ctx, ExpressionAST expr) {
    super(ctx);
    this.expr = expr;
  }

  @Override
  public void check() {

    // Verify that the given expression is valid.
    expr.check();

    // Get the type of the expression.
    IDENTIFIER type = expr.getType();

    if (type != null) {

      if (!(type instanceof PAIR)) {
        // expression is not a pair.
        addError(new MismatchedTypes(ctx, type, new PAIR()));
      }

    }

  }

  public ExpressionAST getExpr(){
    return expr;
  }


  @Override
  public List<Instruction> translate(List<Register> registers) {

    Register target = registers.get(0);

    // Translate expression.
    List<Instruction> ret = new ArrayList<>(expr.translate(registers));

    // Load result into R0
    ret.add(new Move(new Register(0), target));

    // Add branch to p_free_pair
    ret.add(new Branch("p_free_pair", true));

    program.addPrimitive(FreeFunction.printPairFree(program));

    return ret;
  }

  @Override
  public List<Instruction> accept(NodeASTVisitor visitor) {
    return (List<Instruction>) visitor.visit(this);
  }
}
