// import ANTLR's runtime libraries

import antlr.WaccLexer;
import antlr.WaccParser;
import antlr.WaccParser.ProgContext;
import backend.WaccCodeGeneratorVisitor;
import errors.WaccError;
import frontend.syntactic_parser.SyntacticParser;
import frontend.syntactic_parser.SyntaxErrorListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import middleware.NodeAST;
import middleware.WaccASTParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

enum ErrorCode {
  SUCCESS,
  SYNTAX_ERROR,
  SEMANTIC_ERROR,
  FAIL;

  public int code() {
    switch (this) {
      case SUCCESS:
        return 0;
      case SYNTAX_ERROR:
        return 100;
      case SEMANTIC_ERROR:
        return 200;
      case FAIL:
      default:
        return -1;
    }
  }

}

public class WaccCompiler {

  private final WaccParser parser;
  private String sourceCode;

  private final List<WaccError> errors = new ArrayList<>();

  private final SyntaxErrorListener syntaxErrorListener = new SyntaxErrorListener();

  public WaccCompiler(String inputString) throws IOException {
    this(new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8)));
  }

  public WaccCompiler(InputStream inputStream) throws IOException {
    CharStream input = CharStreams.fromStream(inputStream);

    WaccLexer lexer = new WaccLexer(input);
    lexer.removeErrorListeners();

    CommonTokenStream tokens = new CommonTokenStream(lexer);

    parser = new WaccParser(tokens);

    parser.removeErrorListeners();
    parser.addErrorListener(syntaxErrorListener);

    SyntaxErrorListener.setSyntacticErrors(errors);
    NodeAST.setSemanticErrors(errors);

    inputStream.close();
  }

  public ErrorCode compile() {

    ProgContext parseTree = parseSyntactics();

    if (hasErrors()) {

      // we don't do semantic analysis if syntax fails
      return ErrorCode.SYNTAX_ERROR;

    }

    NodeAST ASTtree = parseSemantics(parseTree);

    if (hasErrors()) {
      // don't do code generation if semantic analysis fails
      return ErrorCode.SEMANTIC_ERROR;

    }

    sourceCode = translateCode(ASTtree);

    if (hasErrors()) {

      return ErrorCode.FAIL;

    }

    return ErrorCode.SUCCESS;

  }

  public ProgContext parseSyntactics() {

    SyntacticParser syntacticParser = new SyntacticParser(syntaxErrorListener);

    ProgContext progParseTree = parser.prog();

    // only do further syntax analysis if tokenization passed
    if (!syntaxErrorListener.hasErrors()) {

      syntacticParser.visit(progParseTree);

    }

    return progParseTree;

  }

  public NodeAST parseSemantics(ProgContext progParseTree) {

    WaccASTParser semanticParser = new WaccASTParser();

    NodeAST tree = semanticParser.visit(progParseTree);

    tree.check();

    return tree;

  }

  public String translateCode(NodeAST ASTtree) {

    WaccCodeGeneratorVisitor codeGenerator = new WaccCodeGeneratorVisitor();

    codeGenerator.visit(ASTtree);

    return codeGenerator.toString();

  }

  public String getSourceCode() {
    return sourceCode;
  }

  public List<WaccError> getErrors() {
    return errors;
  }

  public boolean hasErrors() {

    return !errors.isEmpty();

  }


}