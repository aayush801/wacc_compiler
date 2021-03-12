package wacc.frontend.syntactic_parser;

import wacc.errors.WaccError;
import wacc.errors.syntax_errors.WaccSyntaxError;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.Utils;

public class SyntaxErrorListener extends BaseErrorListener {

  private List<WaccError> errors;

  public SyntaxErrorListener(List<WaccError> errors){
    this.errors = errors;
  }

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
      int line, int charPositionInLine, String msg, RecognitionException e) {

    errors.add(
        new WaccSyntaxError(offendingSymbol, line, charPositionInLine, msg));
  }

  public List<WaccError> getErrors() {
    return errors;
  }

  public String toString() {
    return Utils.join(errors.iterator(), "\n");
  }

  public boolean hasErrors() {
    return !errors.isEmpty();
  }


}