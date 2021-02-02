package front_end;

import antlr.BasicParser;
import antlr.BasicParser.*;
import antlr.BasicParserBaseVisitor;
import org.antlr.v4.runtime.tree.*;

public class WaccSemanticParser extends BasicParserBaseVisitor<Void> {

  @Override
  public Void visitProg(ProgContext ctx) {
    System.out.println("Hello");
    return visitChildren(ctx);
  }

  @Override
  public Void visitBinaryOper(BasicParser.BinaryOperContext ctx) {
    // Problem: We are not actually getting a BinaryOper, but directly a '+'
    System.out.println("Is this a plus? " + ctx.PLUS().toString());
    return null;
  }

  @Override
  public Void visitExpr(BasicParser.ExprContext ctx) {
    System.out.println("we have an expression!!");
    return visitChildren(ctx);
  }

  @Override
  public Void visitTerm3(BasicParser.Term3Context ctx) {
    System.out.println("we have a term3!!");
    return visitChildren(ctx);
  }

  @Override public Void visitTerm2(BasicParser.Term2Context ctx) {
    System.out.println("we have a term2!!");
    return visitChildren(ctx);
  }

  @Override public Void visitTerm1(BasicParser.Term1Context ctx) {
    System.out.println("we have a term1!!");
    return visitChildren(ctx);
  }

  @Override public Void visitNum(BasicParser.NumContext ctx) {
    System.out.println("It's a number!!");
    System.out.println("Value: " + ctx.INTEGER().toString());
    return null; // Reached the end, so send a null/whatever back
  }

  @Override public Void visitOtherExpr(BasicParser.OtherExprContext ctx) {
    return visitChildren(ctx);
  }






}
