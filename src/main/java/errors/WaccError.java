package errors;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public abstract class WaccError extends BaseErrorListener {

  private String code;
  private final int lineNo, lineCol;


  public WaccError(int line, int charPositionInLine, String code) {
    this.lineNo = line;
    this.lineCol = charPositionInLine;
    this.code = code;
  }

  public WaccError(Token token) {
    this.code = token.getText();
    this.lineNo = token.getLine();
    this.lineCol = token.getCharPositionInLine();
  }

  public WaccError(Token token, String offendingSymbol) {
    this(token);
    highlightOffendingSymbol(offendingSymbol);
  }

  private void highlightOffendingSymbol(String offendingSymbol) {
    int position = code.indexOf(offendingSymbol);
    code = code.substring(0, position) + " ->" + code.substring(position);
  }


  public abstract String getErrorMessage();

  @Override
  public String toString() {
    return ": " + code + " found at line " + lineNo + ":" + lineCol + " " + getErrorMessage();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof WaccError) {
      return toString().equals(o.toString());
    }
    return false;
  }
}
