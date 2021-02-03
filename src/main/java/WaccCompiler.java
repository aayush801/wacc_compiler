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
import symbol_table.SymbolTable;
import symbol_table.identifier_objects.basic_types.Bool;
import symbol_table.identifier_objects.basic_types.Char;
import symbol_table.identifier_objects.basic_types.Int;
import symbol_table.identifier_objects.basic_types.Pair;
import symbol_table.identifier_objects.basic_types.Str;

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
    SymbolTable topSt = new SymbolTable();
    topSt.add("int", new Int(Integer.MIN_VALUE, Integer.MAX_VALUE));
    topSt.add("string", new Str());
    topSt.add("char", new Char(0, 255));
    topSt.add("pair", new Pair());
    topSt.add("bool", new Bool(0, 1));

    WaccSemanticeExpressionParser myParser = new WaccSemanticeExpressionParser(topSt);
    ParseTree tree = parser.prog();
    myParser.visit(tree);
  }
}
