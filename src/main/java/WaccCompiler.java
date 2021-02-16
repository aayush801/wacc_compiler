// import ANTLR's runtime libraries

import antlr.WaccLexer;
import antlr.WaccParser;
import antlr.WaccParser.ProgContext;
import backend.instructions.Instruction;
import backend.instructions.Label;
import backend.registers.LinkRegister;
import backend.registers.ProgramCounter;
import backend.registers.Register;
import backend.registers.StackPointer;
import errors.WaccError;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import middleware.NodeAST;
import middleware.WaccASTParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import frontend.syntactic_parser.SyntacticParser;
import frontend.syntactic_parser.SyntaxErrorListener;

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
    lexer.removeErrorListeners();

    CommonTokenStream tokens = new CommonTokenStream(lexer);

    parser = new WaccParser(tokens);

    parser.removeErrorListeners();
    parser.addErrorListener(syntaxErrorListener);

    NodeAST.setSemanticErrors(errors);

    inputStream.close();
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

  public NodeAST parseSemantics(ProgContext AST) {
    NodeAST.reset();
    NodeAST tree = semanticParser.visit(AST);
    tree.check();
    return tree;
  }

  public void translateCode(NodeAST tree){
    List<Register> registers = new ArrayList<>();
    for (int i = 0; i <= 12; i++) {
      registers.add(new Register(i));
    }
    registers.add(new ProgramCounter());
    registers.add(new LinkRegister());
    registers.add(new StackPointer());

    List<Instruction> mainInstructs = tree.translate(registers);
    mainInstructs.forEach(System.out::println);
  }

  public List<WaccError> getErrors() {
    return errors;
  }

  public boolean hasErrors() {

    return !errors.isEmpty();

  }

}