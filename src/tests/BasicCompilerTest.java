package tests;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import main.Compiler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


// import ANTLR's runtime libraries
// import antlr package

public class BasicCompilerTest {

  private final Compiler compiler = new Compiler();

  private int compileInstruction(String instruction) throws IOException {
    int code = compiler.compile(new ByteArrayInputStream((instruction).getBytes(StandardCharsets.UTF_8)));
    assertThat(code, is(0));
    return code;
  }

  @Test
  public void testAddition() throws IOException {
    String instruction = "begin (1+2) end";
    compileInstruction(instruction);
    assertThat(compiler.treeString(), is("(prog (expr (expr 1) (binaryOper +) (expr 2)) <EOF>)"));
  }




    // Input:
    // A .wacc file

    // Main body:
    // make tokens(lexical analysis)
    // parse it
    // do semantic analysis

    // Return:
    // 0 if it was OK
    // 100 if one or more syntax errors
    // 200 if one or more semantic errors

}
