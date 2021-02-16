package frontend.syntactic_parser;

import antlr.WaccParser;
import antlr.WaccParser.IntLiterContext;
import antlr.WaccParserBaseVisitor;
import frontend.identifier_objects.basic_types.INT;
import org.antlr.v4.runtime.tree.ParseTree;

public class SyntacticParser extends WaccParserBaseVisitor<Boolean> {

  private final SyntaxErrorListener listener;

  /* MAIN PURPOSE OF THIS CLASS IS TO CATCH SYNTAX ERROR THAT ANTLR CANNOT */
  public SyntacticParser(SyntaxErrorListener listener) {
    this.listener = listener;
  }

  @Override
  public Boolean visit(ParseTree tree) {
    // overrides the default visit such that it will return false for all nodes
    // that are not defined
    Boolean bool = tree.accept(this);
    return bool != null && bool;
  }

  /* SPECIFICALLY DEALS WITH STATEMENT BLOCKS THAT DO NOT ENCLOSE A RETURN STATEMENT */
  @Override
  public Boolean visitFuncDecl(WaccParser.FuncDeclContext ctx) {
    Boolean statHasReturn = visit(ctx.stat());
    if (!statHasReturn) {
      listener.syntaxError(null, ctx.IDENT().getText(), ctx.getStart().getLine(),
          ctx.getStart().getCharPositionInLine(),
          "Function " + ctx.IDENT().getText() + " is not ended with a return or an exit statement.",
          null);
    }
    return visitChildren(ctx);
  }

  @Override
  public Boolean visitIfThenElse(WaccParser.IfThenElseContext ctx) {
    return visit(ctx.stat(0)) && visit(ctx.stat(1));
  }

  @Override
  public Boolean visitWhileDo(WaccParser.WhileDoContext ctx) {
    return visit(ctx.stat());
  }


  @Override
  public Boolean visitReturnCall(WaccParser.ReturnCallContext ctx) {
    return true;
  }

  @Override
  public Boolean visitExitCall(WaccParser.ExitCallContext ctx) {
    return true;
  }
  /* ======================== END ============================== */


  /* SPECIFICALLY DEALS WITH OVERFLOWS AND UNSUPPORTED SYNTAX */


  @Override
  public Boolean visitIntLiter(IntLiterContext ctx) {
    String integer = ((ctx.MINUS() == null) ? "+" : "-") + ctx.INTEGER().getText();
    if (!new INT().check(integer)) {
      listener.syntaxError(null, ctx.getText(), ctx.getStart().getLine(),
          ctx.getStart().getCharPositionInLine(),
          "is badly formatted (either it has a badly defined sign or it is too large for a 32-bit signed integer",
          null);
    }
    return null;
  }

}
