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

  private void compileInstruction(String instruction) throws IOException {
    compiler.compile(new ByteArrayInputStream((instruction).getBytes(StandardCharsets.UTF_8)));
  }

  @Test
  public void testAddition() throws IOException {
    String instruction = "1 + 2";
    compileInstruction(instruction);
    assertThat(compiler.treeString(), is("(prog (expr (expr 1) (binaryOper +) (expr 2)) <EOF>)"));
  }

  @Test
  public void testComments() throws IOException {
    String instruction = " # lmao \n";
    compileInstruction(instruction);
    assertThat(compiler.treeString(), is("(prog <EOF>)"));
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
