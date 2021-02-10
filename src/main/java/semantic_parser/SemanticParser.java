package semantic_parser;

import antlr.WaccParser;
import antlr.WaccParser.FuncDeclContext;
import semantic_parser.statements.SemanticStatementParser;

public class SemanticParser extends SemanticStatementParser {

  @Override
  public Object visitProg(WaccParser.ProgContext ctx) {
    for (FuncDeclContext func : ctx.funcDecl()) {
      visitFuncHeader(func);
    }
    for (FuncDeclContext func : ctx.funcDecl()) {
      visitFuncBody(func);
    }
    return visit(ctx.stat());
  }

}