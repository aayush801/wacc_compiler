package errors.syntax_errors;

import errors.WaccError;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class WaccSyntaxError extends WaccError {

  private final String msg;

  public WaccSyntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
      int charPositionInLine, String msg, RecognitionException e) {
    super(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
    this.msg = msg;
  }

  @Override
  public String getErrorMessage() {
    return msg;
  }

  @Override
  public String toString() {
    return "Syntax Error " + super.toString();
  }
}
