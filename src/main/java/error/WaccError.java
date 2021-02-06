package error;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class WaccError {
  protected String partOfCodeWithError;
  protected String code;
  protected int lineNo;
  protected int lineCol;
  protected ParserRuleContext ctx;

  public WaccError(ParserRuleContext ctx) {
    this.ctx = ctx;
    this.code = ctx.getText();
    this.lineNo = ctx.getStart().getLine();
    this.lineCol = ctx.getStart().getCharPositionInLine();
  }

  public WaccError(ParserRuleContext ctx, String partOfCodeWithError) {
    this(ctx);
    int position = code.indexOf(partOfCodeWithError);
    code = code.substring(0, position) + "->" + code.substring(position);
  }

  public String getErrorMessage() {
    System.out.println("This method should be override");
    return null;
  }

  @Override
  public String toString() {
    return "line " + this.lineNo + ":" + this.lineCol + " " + getErrorMessage();
  }

  @Override
  public boolean equals(Object o) {
    if(o instanceof WaccError) {
      return toString().equals(o.toString());
    }
    return false;
  }
}
