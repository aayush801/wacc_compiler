import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

// import ANTLR's runtime libraries
// import antlr package

public class SyntaxTests {

  private final WaccCompiler compiler = new WaccCompiler();

  private void compileInstruction(String instruction) throws IOException {
    compiler.compile(new ByteArrayInputStream((instruction).getBytes(StandardCharsets.UTF_8)));
  }

  @Test
  public void testAddition() throws IOException {
    String instruction = "1 + 2";
    compileInstruction(instruction);
    assertThat(compiler.treeString(),is("(prog (expr (expr 1) + (expr 2)) <EOF>)"));
  }

  @Test
  public void testPrecedence() throws IOException {
    String instruction = "1 + 2 * 3 / 2";
    compileInstruction(instruction);
    assertThat(compiler.treeString(),
        is("(prog (expr (expr 1) + (expr (expr (expr 2) * (expr 3)) / (expr 2))) <EOF>)"));
  }

  @Test
  public void testComments() throws IOException {
    String instruction = "# random comment \n";
    compileInstruction(instruction);
    assertThat(compiler.treeString(), is("(prog <EOF>)"));
  }

  @Test
  public void testLessThan() throws IOException {
    String instruction = "1<c";
    compileInstruction(instruction);
    assertThat(compiler.treeString(), is("(prog (expr (expr 1) < (expr c)) <EOF>)"));
  }

  @Test
  public void testEquality() throws IOException {
    String instruction = "true && (2 == 3)";
    compileInstruction(instruction);
    // THIS DOESN'T WORK YET!
    //     | (boolExpr | PAIR | IDENT | STRING | CHARACTER | arrayElem | unaryOper expr | OPEN_PARENTHESES expr CLOSE_PARENTHESES | term2) equalityOp expr
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
