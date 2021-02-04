// import ANTLR's runtime libraries

import antlr.WaccLexer;
import antlr.WaccParser;
import error_handlers.WaccParserHandler;
import front_end.SemanticParser;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class WaccCompiler {

  private CommonTokenStream tokens;
  private WaccParser parser;
  private final WaccParserHandler parserHandler = new WaccParserHandler();

  public void compile(InputStream inputStream) throws IOException {

    CharStream input = CharStreams.fromStream(inputStream);

    WaccLexer lexer = new WaccLexer(input);

    tokens = new CommonTokenStream(lexer);
    parser = new WaccParser(tokens);

    parseSyntactics();
  }

  public String treeString() {
    return parser.prog().toStringTree(parser);
  }

  public void parseSyntactics() {
    parser.addErrorListener(parserHandler);
  }

  public void parseSemantics() {
    SemanticParser semanticParser = new SemanticParser();
    ParseTree tree = parser.prog();
    semanticParser.visit(tree);
  }
}
