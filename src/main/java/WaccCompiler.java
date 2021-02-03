// import ANTLR's runtime libraries

import antlr.WaccLexer;
import antlr.WaccParser;
import error_handlers.WaccParserHandler;
import front_end.WaccSemanticeExpressionParser;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class WaccCompiler {
  private WaccLexer lexer;
  private CommonTokenStream tokens;
  private WaccParser parser;
  private final WaccParserHandler parserHandler = new WaccParserHandler();

  public void compile(InputStream inputStream) throws IOException {

    CharStream input = CharStreams.fromStream(inputStream);

    lexer = new WaccLexer(input);

    tokens = new CommonTokenStream(lexer);

    analyseSyntactics();
  }

  public String treeString(){
    return parser.prog().toStringTree(parser);
  }

  public void analyseSyntactics() {
    parser = new WaccParser(tokens);
    parser.addErrorListener(parserHandler);
  }

  public void analyseSemantics() {
    WaccSemanticeExpressionParser myParser = new WaccSemanticeExpressionParser();
    ParseTree tree = parser.prog();
    myParser.visit(tree);
  }
}
