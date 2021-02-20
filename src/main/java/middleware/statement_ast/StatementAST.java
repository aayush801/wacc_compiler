package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.operands.Immediate;
import java.util.List;
import middleware.NodeAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class StatementAST extends NodeAST {

  // superclass of all statement AST classes.


  public StatementAST(ParserRuleContext ctx) {
    super(ctx);
  }



}
