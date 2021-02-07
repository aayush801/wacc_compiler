// import ANTLR's runtime libraries

import antlr.WaccLexer;
import antlr.WaccParser;
import antlr.WaccParser.ProgContext;
import error_handlers.SyntaxErrorListener;
import error_handlers.WaccErrorCode;
import errors.WaccError;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import semantic_parser.SemanticParserVisitor;

public class WaccCompiler {

  private final List<WaccError> errors = new ArrayList<>();
  private final WaccParser syntaxParser;
  private final SemanticParserVisitor semanticParserVisitor = new SemanticParserVisitor();
  private final SyntaxErrorListener syntaxErrorListener = new SyntaxErrorListener();

  public WaccCompiler(InputStream inputStream) throws IOException {
    CharStream input = CharStreams.fromStream(inputStream);

    WaccLexer lexer = new WaccLexer(input);

    CommonTokenStream tokens = new CommonTokenStream(lexer);

    syntaxParser = new WaccParser(tokens);
  }

  public WaccErrorCode compile() {

    parseSemantics(parseSyntactics());

    if (syntaxErrorListener.hasErrors()) {

      return WaccErrorCode.SYNTAX_ERROR;

    } else if (semanticParserVisitor.hasErrors()) {

      return WaccErrorCode.SEMANTIC_ERROR;

    } else {

      return WaccErrorCode.SUCCESS;

    }

  }

  public ProgContext parseSyntactics() {

    syntaxParser.removeErrorListeners();
    syntaxParser.addErrorListener(syntaxErrorListener);

    ProgContext progContext = syntaxParser.prog();

    errors.addAll(syntaxErrorListener.getErrors());


    return progContext;

  }

  public void parseSemantics(ProgContext AST) {

    semanticParserVisitor.visit(AST);

    errors.addAll(semanticParserVisitor.getErrors());

  }

  public List<WaccError> getErrors() {

    return errors;

  }

  public boolean hasErrors() {

    return errors.size() > 0;

  }

}
