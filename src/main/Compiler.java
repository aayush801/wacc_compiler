package main;

// import ANTLR's runtime libraries
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.*;

// import antlr package
import antlr.*;

public class Compiler {
  private BasicLexer lexer;
  private CommonTokenStream tokens;
  private BasicParser parser;

  public int compile(InputStream inputStream) throws IOException {

    CharStream input = CharStreams.fromStream(inputStream);

    lexer = new BasicLexer(input);

    tokens = new CommonTokenStream(lexer);

    parser = new BasicParser(tokens);

    return 0; // successful compilation

  }

  public String treeString(){
    return parser.prog().toStringTree(parser);
  }




}
