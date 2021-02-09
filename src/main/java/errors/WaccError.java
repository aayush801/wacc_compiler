package errors;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public abstract class WaccError extends BaseErrorListener {

  protected String code;
  protected int lineNo;
  protected int lineCol;
  protected ParserRuleContext ctx;

  public WaccError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
      int charPositionInLine, String msg, RecognitionException e) {
    this.lineNo = line;
    this.lineCol = charPositionInLine;
  }

  public WaccError(ParserRuleContext ctx) {
    this.ctx = ctx;
    this.code = ctx.getText();
    this.lineNo = ctx.getStart().getLine();
    this.lineCol = ctx.getStart().getCharPositionInLine();
  }

  public WaccError(ParserRuleContext ctx, String offendingSymbol) {
    this(ctx);
    int position = code.indexOf(offendingSymbol);
    code = code.substring(0, position) + "->" + code.substring(position);
  }

  protected WaccError() {
  }

  public String getErrorMessage() {
    System.out.println("THIS METHOD SHOULD BE OVERRIDDEN");
    return null;
  }

  @Override
  public String toString() {
    return "found at line " + lineNo + ":" + lineCol + " " + getErrorMessage();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof WaccError) {
      return toString().equals(o.toString());
    }
    return false;
  }
}
