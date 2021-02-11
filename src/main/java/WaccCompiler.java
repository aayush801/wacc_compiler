// import ANTLR's runtime libraries

import antlr.WaccLexer;
import antlr.WaccParser;
import antlr.WaccParser.ProgContext;
import errors.WaccError;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import middleware.ast.NodeAST;
import middleware.ast.WaccASTParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import semantic_parser.SemanticParser;
import syntactic_parser.SyntacticParser;
import syntactic_parser.SyntaxErrorListener;

public class WaccCompiler {
  private final WaccParser parser;

  private final List<WaccError> errors = new ArrayList<>();
  private final SyntaxErrorListener syntaxErrorListener = new SyntaxErrorListener();

  private final WaccASTParser semanticParser = new WaccASTParser();//new SemanticParser();
  private final SyntacticParser syntacticParser = new SyntacticParser(syntaxErrorListener);

  public WaccCompiler(String inputString) throws IOException {
    this(new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8)));
  }

  public WaccCompiler(InputStream inputStream) throws IOException {
    CharStream input = CharStreams.fromStream(inputStream);

    WaccLexer lexer = new WaccLexer(input);
    inputStream.close();
    lexer.removeErrorListeners();

    CommonTokenStream tokens = new CommonTokenStream(lexer);

    parser = new WaccParser(tokens);

    parser.removeErrorListeners();
    parser.addErrorListener(syntaxErrorListener);

    NodeAST.setSemanticErrors(errors);
  }

  public ErrorCode compile() {

    ProgContext AST = parseSyntactics();

    if (syntaxErrorListener.hasErrors()) {
      // we dont do semantic analysis if syntax fails
      return ErrorCode.SYNTAX_ERROR;

    }

    parseSemantics(AST);

    if (!errors.isEmpty()) {

      return ErrorCode.SEMANTIC_ERROR;

    }

    return ErrorCode.SUCCESS;


  }

  public ProgContext parseSyntactics() {

    ProgContext AST = parser.prog();

    // only do further syntax analysis if tokenization passed
    if (!syntaxErrorListener.hasErrors()) {
      syntacticParser.visit(AST);
    }

    errors.addAll(syntaxErrorListener.getErrors());

    return AST;

  }

  public void parseSemantics(ProgContext AST) {

    NodeAST tree = semanticParser.visit(AST);
    tree.check();
  }

  public List<WaccError> getErrors() {
    return errors;
  }

  public boolean hasErrors() {

    return !errors.isEmpty();

  }

}
