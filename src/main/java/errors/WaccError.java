package errors;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class WaccError extends BaseErrorListener {

  private final int lineNo, lineCol;
  private String code;


  public WaccError(int line, int charPositionInLine, String code) {
    this.lineNo = line;
    this.lineCol = charPositionInLine;
    this.code = code;
  }

  public WaccError(ParserRuleContext ctx) {
    this.code = ctx.getText();
    this.lineNo = ctx.getStart().getLine();
    this.lineCol = ctx.getStart().getCharPositionInLine();
  }

  public WaccError(ParserRuleContext ctx, String offendingSymbol) {
    this(ctx);
    highlightOffendingSymbol(offendingSymbol);
  }

  private void highlightOffendingSymbol(String offendingSymbol) {
    int position = code.indexOf(offendingSymbol);
    code = code.substring(0, position) + "->" + code.substring(position);
  }


  public abstract String getErrorMessage();

  @Override
  public String toString() {
    return ": " + code + " found at line " + lineNo + ":" + lineCol + " " + getErrorMessage();
  }


}
