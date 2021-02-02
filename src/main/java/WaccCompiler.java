// import ANTLR's runtime libraries

import antlr.BasicLexer;
import antlr.BasicParser;
import error_handlers.WaccParserHandler;
import front_end.WaccSemanticParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;

import java.io.IOException;
import java.io.InputStream;

public class WaccCompiler {
  private BasicLexer lexer;
  private CommonTokenStream tokens;
  private BasicParser parser;
  private final WaccParserHandler parserHandler = new WaccParserHandler();

  public void compile(InputStream inputStream) throws IOException {

    CharStream input = CharStreams.fromStream(inputStream);

    lexer = new BasicLexer(input);

    tokens = new CommonTokenStream(lexer);

    parser = new BasicParser(tokens);

    parser.addErrorListener(parserHandler);
  }

  public String treeString(){
    return parser.prog().toStringTree(parser);
  }

  public void traverseAST() {
    System.out.println("=======");
    WaccSemanticParser myParser = new WaccSemanticParser();
    myParser.visit(parser.prog());
    System.out.println("=======");
  }
}
