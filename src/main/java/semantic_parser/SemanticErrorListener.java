package semantic_parser;

import errors.WaccError;
import errors.syntax_errors.WaccSyntaxError;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.Utils;

public class SemanticErrorListener {

  private final List<WaccError> errors;

  public SemanticErrorListener() {
    errors = new ArrayList<>();
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

  public void add(WaccError e) {
    errors.add(e);
  }
}
