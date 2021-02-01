package error_handlers;

import org.antlr.v4.runtime.*;

public class WaccParserHandler extends BaseErrorListener {

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s,
      RecognitionException e) {
    System.exit(WaccError.SYNTAX_ERROR.code());
  }

}