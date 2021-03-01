package middleware.statement_ast;

import backend.NodeASTVisitor;
import backend.instructions.Instruction;
import java.util.List;
import middleware.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

/*
 * Basic node for the skip statement.
 */
public class SkipAST extends StatementAST {


  public SkipAST(ParserRuleContext ctx) {
    super(ctx);
  }

  @Override
  public void check() {
  }

  @Override
  public List<Instruction> accept(NodeASTVisitor visitor) {
    return (List<Instruction>) visitor.visit(this);
  }

}
