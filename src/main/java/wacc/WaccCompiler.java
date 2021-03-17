package wacc;// import ANTLR's runtime libraries

import antlr.WaccLexer;
import antlr.WaccParser;
import antlr.WaccParser.ProgContext;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.w3c.dom.Node;
import wacc.backend.WaccTranslator;
import wacc.errors.WaccError;
import wacc.extension.ControlFlowAnalyser;
import wacc.frontend.syntactic_parser.SyntacticParser;
import wacc.frontend.syntactic_parser.SyntaxErrorListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import wacc.middleware.NodeAST;
import wacc.middleware.WaccASTParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import wacc.middleware.symbol_table.SymbolTable;

public class WaccCompiler {

  private final WaccParser parser;
  private final List<WaccError> errors = new ArrayList<>();
  private final SyntaxErrorListener syntaxErrorListener
      = new SyntaxErrorListener(errors);
  private String sourceCode;
  private String filename = "default";
  private Path relativePath;

  public WaccCompiler(String instructions) throws IOException {
    this(new ByteArrayInputStream(instructions.getBytes(StandardCharsets.UTF_8)));
  }

  public WaccCompiler(File file) throws IOException {
    this(new FileInputStream(file));
    setRelativePath(file.getAbsolutePath());
    this.filename = file.getName();
  }

  public WaccCompiler(InputStream inputStream) throws IOException {
    CharStream input = CharStreams.fromStream(inputStream);

    WaccLexer lexer = new WaccLexer(input);
    lexer.removeErrorListeners();

    CommonTokenStream tokens = new CommonTokenStream(lexer);

    parser = new WaccParser(tokens);

    parser.removeErrorListeners();
    parser.addErrorListener(syntaxErrorListener);

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
    ProgContext parseTree = parser.prog();

    // only do further syntax analysis if tokenization passed
    if (!syntaxErrorListener.hasErrors()) {
      syntacticParser.visit(parseTree);
    }

    if (syntaxErrorListener.hasErrors()) {
      return null;
    }

    return parseTree;
  }

  public NodeAST parseSemantics(ProgContext parseTree) {

    if (parseTree == null) {
      return null;
    }

    WaccASTParser semanticParser = new WaccASTParser(filename, relativePath, errors);

    NodeAST tree = semanticParser.visit(parseTree);

    tree.check();

    if (!hasErrors()) {
      // Control flow analysis of AST nodes
      NodeAST prog = tree.accept(new ControlFlowAnalyser());
      prog.check();
      return prog;
    }

    return tree;
  }

  public String translateCode(NodeAST ASTtree) {
    WaccTranslator codeGenerator = new WaccTranslator();

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

  public void setRelativePath(String path) {
    if (path == null) {
      relativePath = null;
    }
    else {
      relativePath = Paths.get(path).getParent();
    }
  }
}